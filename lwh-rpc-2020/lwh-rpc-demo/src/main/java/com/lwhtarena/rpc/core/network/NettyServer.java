package com.lwhtarena.rpc.core.network;

import com.alibaba.fastjson.JSONObject;
import com.lwhtarena.rpc.config.ServiceConfig;
import com.lwhtarena.rpc.util.StringUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.ReferenceCountUtil;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liwh
 * @Title: NettyServer
 * @Package com.lwhtarena.rpc.core.network
 * @Description: 负责管理网络请求组件
 * @Version 1.0.0
 * @date 2020/9/19 22:28
 */
public class NettyServer {


    /**
     * 接口方法唯一标识对应的Method对象
     */
    private Map<String, Method> interfaceMethods;
    /**
     * 接口对应的实现类
     */
    private Map<Class<?>, Object> interfaceToInstance;

    /**
     * 线程池，随意写的，不要吐槽,可以通过配置文件配置
     */
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,
            50, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100),
            new ThreadFactory() {
                AtomicInteger m = new AtomicInteger(0);

                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, "IO-thread-" + m.incrementAndGet());
                }
            });


    public NettyServer(List<ServiceConfig> serverConfigs, Map<String, Method> interfaceMethods) throws InterruptedException {
        this.interfaceToInstance = new ConcurrentHashMap<>();
        this.interfaceMethods = interfaceMethods;
        for (ServiceConfig config : serverConfigs) {
            interfaceToInstance.put(config.getType(), config.getInstance());
        }
    }

    public void init(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ByteBuf delimiter = Unpooled.copiedBuffer("$$".getBytes());
                        // 设置按照分隔符“&&”来切分消息，单条消息限制为 1MB
                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024 * 1024, delimiter));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new RpcInvokeHandler());
                    }
                });
        ChannelFuture sync = b.bind(port).sync();
        System.out.println("启动NettyService，端口为：" + port);
    }


    private class RpcInvokeHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            try {
                String message = (String) msg;
                // 这里拿到的是一串JSON数据，解析为Request对象，
                // 事实上这里解析网络数据，可以用序列化方式，定一个接口，可以实现JSON格式序列化，或者其他序列化
                // 但是demo版本就算了。
                System.out.println("接收到消息：" + msg);
                RpcRequest request = RpcRequest.parse(message, ctx);
                threadPoolExecutor.execute(new RpcInvokeTask(request));
            } finally {
                ReferenceCountUtil.release(msg);
            }
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            System.out.println("发生了异常..." + cause);
            ctx.close();
        }
    }

    public class RpcInvokeTask implements Runnable {

        private RpcRequest rpcRequest;

        RpcInvokeTask(RpcRequest rpcRequest) {
            this.rpcRequest = rpcRequest;
        }

        @Override
        public void run() {
            try {
                /*
                 * 数据大概是这样子的
                 * {"interfaces":"interface=com.study.rpc.test.producer.HelloService&method=sayHello&parameter=com
                 * .study.rpc.test.producer.TestBean","requestId":"3","parameter":{"com.study.rpc.test.producer
                 * .TestBean":{"age":20,"name":"张三"}}}
                 */
                // 这里希望能拿到每一个服务对象的每一个接口的特定声明
                String interfaceIdentity = rpcRequest.getInterfaceIdentity();
                Method method = interfaceMethods.get(interfaceIdentity);
                Map<String, String> map = StringUtils.string2Map(interfaceIdentity);
                String interfaceName = map.get("interface");
                Class<?> interfaceClass = Class.forName(interfaceName);
                Object o = interfaceToInstance.get(interfaceClass);
                String parameterString = map.get("parameter");
                Object result;
                if (parameterString != null) {
                    String[] parameterTypeClass = parameterString.split(",");
                    Map<String, Object> parameterMap = rpcRequest.getParameterMap();
                    Object[] parameterInstance = new Object[parameterTypeClass.length];
                    for (int i = 0; i < parameterTypeClass.length; i++) {
                        String parameterClazz = parameterTypeClass[i];
                        parameterInstance[i] = parameterMap.get(parameterClazz);
                    }
                    result = method.invoke(o, parameterInstance);
                } else {
                    result = method.invoke(o);
                }
                // 写回响应
                ChannelHandlerContext ctx = rpcRequest.getCtx();
                String requestId = rpcRequest.getRequestId();
                RpcResponse response = RpcResponse.create(JSONObject.toJSONString(result), interfaceIdentity,
                        requestId);
                String s = JSONObject.toJSONString(response) + "$$";
                ByteBuf byteBuf = Unpooled.copiedBuffer(s.getBytes());
                ctx.writeAndFlush(byteBuf);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
