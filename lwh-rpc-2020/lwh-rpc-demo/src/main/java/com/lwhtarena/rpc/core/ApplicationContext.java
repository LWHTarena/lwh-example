package com.lwhtarena.rpc.core;

import com.alibaba.fastjson.JSONObject;
import com.lwhtarena.rpc.config.ReferenceConfig;
import com.lwhtarena.rpc.config.ServiceConfig;
import com.lwhtarena.rpc.core.loadbalance.LoadBalancer;
import com.lwhtarena.rpc.core.loadbalance.RandomLoadbalancer;
import com.lwhtarena.rpc.core.network.NettyClient;
import com.lwhtarena.rpc.core.network.NettyServer;
import com.lwhtarena.rpc.core.network.RpcResponse;
import com.lwhtarena.rpc.core.registry.MulticastRegistry;
import com.lwhtarena.rpc.core.registry.Registry;
import com.lwhtarena.rpc.core.registry.RegistryInfo;
import com.lwhtarena.rpc.core.registry.ZookeeperRegistry;
import com.lwhtarena.rpc.util.InvokeUtils;
import io.netty.channel.ChannelHandlerContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author liwh
 * @Title: ApplicationContext
 * @Package com.lwhtarena.rpc.core
 * @Description: 程序上下文
 * @Version 1.0.0
 * @date 2020/9/19 22:24
 */
public class ApplicationContext {

    /**
     * 消费者需要消费的接口
     */
    private List<ReferenceConfig> referenceConfigs;

    /**
     * 每个service接口的实力
     */
    private List<ServiceConfig> serviceConfigs;

    /**
     * 注册中心
     */
    private Registry registry;

    /**
     * NettyServer组件
     */
    private NettyServer nettyServer;

    /**
     * 接口方法对应服务提供者列表
     */
    private Map<Class<?>, List<RegistryInfo>> interfacesMethodRegistryList = new ConcurrentHashMap<>();

    /**
     * 所有服务提供者维护的一份连接
     */
    private Map<RegistryInfo, ChannelHandlerContext> channels = new HashMap<>();

    /**
     * 响应队列
     */
    private ConcurrentLinkedQueue<RpcResponse> responses = new ConcurrentLinkedQueue<>();

    /**
     * 正在请求中的调用
     */
    private Map<String, Invoker> inProgressInvoker = new ConcurrentHashMap<>();

    /**
     * 处理响应结果的线程
     */
    private ResponseProcessor[] processors;

    /**
     * 负载均衡组件
     */
    private LoadBalancer loadBalancer;

    /**
     * 负责生成requestId的类
     */
    private LongAdder requestIdWorker = new LongAdder();

    /**
     * 接口方法对应method对象
     */
    private Map<String, Method> interfaceMethods = new ConcurrentHashMap<>();

    public ApplicationContext(String registryUrl, List<ServiceConfig> serviceConfigs,
                              List<ReferenceConfig> referenceConfigs, int port) throws Exception {
        // step 1: 保存服务提供者和消费者
        this.serviceConfigs = serviceConfigs == null ? new ArrayList<>() : serviceConfigs;
        this.referenceConfigs = referenceConfigs == null ? new ArrayList<>() : referenceConfigs;

        // step 2: 实例化注册中心
        initRegistry(registryUrl);

        // step 3: 将接口注册到注册中心，从注册中心获取接口，初始化服务接口列表
        RegistryInfo registryInfo = null;
        InetAddress addr = InetAddress.getLocalHost();
        String hostname = addr.getHostName();
        String hostAddress = addr.getHostAddress();
        registryInfo = new RegistryInfo(hostname, hostAddress, port);
        doRegistry(registryInfo);


        // step 4：初始化Netty服务器，接受到请求，直接打到服务提供者的service方法中
        if (!this.serviceConfigs.isEmpty()) {
            // 需要暴露接口才暴露
            nettyServer = new NettyServer(this.serviceConfigs, interfaceMethods);
            nettyServer.init(port);
        }

        if (!this.referenceConfigs.isEmpty()) {
            // step 5：启动处理响应的processor
            initProcessor();

            // 实时上，这里可以通过配置，来初始化不同的实现类
            loadBalancer = new RandomLoadbalancer();
        }
    }

    private void initProcessor() {
        // 事实上，这里可以通过配置文件读取，启动多少个processor
        int num = 3;
        processors = new ResponseProcessor[num];
        for (int i = 0; i < 3; i++) {
            processors[i] = createProcessor(i);
        }
    }

    private ResponseProcessor createProcessor(int i) {
        ResponseProcessor processor = new ResponseProcessor();
        processor.setDaemon(true);
        processor.setName("Response-processor-" + i);
        processor.start();
        return processor;
    }

    private void doRegistry(RegistryInfo registryInfo) throws Exception {
        for (ServiceConfig config : serviceConfigs) {
            Class type = config.getType();
            registry.register(type, registryInfo);
            Method[] declaredMethods = type.getDeclaredMethods();
            for (Method method : declaredMethods) {
                String identify = InvokeUtils.buildInterfaceMethodIdentify(type, method);
                interfaceMethods.put(identify, method);
            }
        }
        for (ReferenceConfig config : referenceConfigs) {
            List<RegistryInfo> registryInfos = registry.fetchRegistry(config.getType());
            if (registryInfos != null) {
                interfacesMethodRegistryList.put(config.getType(), registryInfos);
                initChannel(registryInfos);
            }
        }
    }

    private void initChannel(List<RegistryInfo> registryInfos) throws InterruptedException {
        for (RegistryInfo info : registryInfos) {
            if (!channels.containsKey(info)) {
                System.out.println("开始建立连接：" + info.getIp() + ", " + info.getPort());
                NettyClient client = new NettyClient(info.getIp(), info.getPort());
                client.setMessageCallback(message -> {
                    // 这里收单服务端返回的消息，先压入队列
                    RpcResponse response = JSONObject.parseObject(message, RpcResponse.class);
                    responses.offer(response);
                    synchronized (ApplicationContext.this) {
                        ApplicationContext.this.notifyAll();
                    }
                });

                // 等待连接建立
                ChannelHandlerContext ctx = client.getCtx();
                channels.put(info, ctx);
            }
        }
    }

    private void initRegistry(String registryUrl) {
        if (registryUrl.startsWith("zookeeper://")) {
            registryUrl = registryUrl.substring(12);
            registry = new ZookeeperRegistry(registryUrl);
        } else if (registryUrl.startsWith("multicast://")) {
            registry = new MulticastRegistry(registryUrl);
        }
    }

    /**
     * 获取调用服务
     */
    @SuppressWarnings("unchecked")
    public <T> T getService(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                if ("equals".equals(methodName) || "hashCode".equals(methodName)) {
                    throw new IllegalAccessException("不能访问" + methodName + "方法");
                }
                if ("toString".equals(methodName)) {
                    return clazz.getName() + "#" + methodName;
                }


                // step 1: 获取服务地址列表
                List<RegistryInfo> registryInfos = interfacesMethodRegistryList.get(clazz);

                if (registryInfos == null) {
                    throw new RuntimeException("无法找到服务提供者");
                }

                // step 2： 负载均衡
                RegistryInfo registryInfo = loadBalancer.choose(registryInfos);


                ChannelHandlerContext ctx = channels.get(registryInfo);
                String identify = InvokeUtils.buildInterfaceMethodIdentify(clazz, method);
                String requestId;
                synchronized (ApplicationContext.this) {
                    requestIdWorker.increment();
                    System.out.println("生成的requestId = " + requestIdWorker.longValue());
                    requestId = String.valueOf(requestIdWorker.longValue());
                }
                Invoker invoker = new DefaultInvoker(method.getReturnType(), ctx, requestId, identify);
                inProgressInvoker.put(identify + "#" + requestId, invoker);
                return invoker.invoke(args);
            }
        });
    }

    /**
     * 处理响应的线程
     */
    private class ResponseProcessor extends Thread {
        @Override
        public void run() {
            System.out.println("启动响应处理线程：" + getName());
            while (true) {
                // 多个线程在这里获取响应，只有一个成功
                RpcResponse response = responses.poll();
                if (response == null) {
                    try {
                        synchronized (ApplicationContext.this) {
                            // 如果没有响应，先休眠
                            ApplicationContext.this.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    String interfaceMethodIdentify = response.getInterfaceMethodIdentify();
                    String requestId = response.getRequestId();
                    String key = interfaceMethodIdentify + "#" + requestId;
                    Invoker invoker = inProgressInvoker.remove(key);
                    invoker.setResult(response.getResult());
                }
            }
        }
    }

}
