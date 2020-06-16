package com.lwhtarena.dubborpc.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liwh
 * @Title: NettyClient
 * @Package com.lwhtarena.dubborpc.netty
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/16 04:12
 */
public class NettyClient {

    /**
     * （根据CPU）获取有效线程
     */
    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static NettyClientHandler client;
    private int count =0;

    /**使用代理对象，获取一个代理对象**/
    public Object getBean(final Class<?> serviceClass,final String providerName){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class<?>[]{serviceClass},(proxy,method,args) ->{

            System.out.println("(proxy, method, args) 进入...." + (++count) + " 次");
            if(client == null){
                initClient();
            }

            /**
             * 设置参数发给服务器端的信息
             * providerName 协议头 arg[0] 就是客户端调用api hello（？？？）
             */
            client.setPara(providerName+args[0]);
            return executor.submit(client).get();
        });
    }

    /**
     * 初始化客户端
     */
    private static void initClient(){
        /**创建EventLoopGroup**/
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            client =new NettyClientHandler();

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            ChannelPipeline pipeline = sc.pipeline();
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(client);
                        }
                    });
            bootstrap.connect("127.0.0.1", 7000).sync();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
