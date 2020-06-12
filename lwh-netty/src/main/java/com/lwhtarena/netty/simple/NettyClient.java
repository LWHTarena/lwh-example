package com.lwhtarena.netty.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author liwh
 * @Title: NettyClient
 * @Package com.lwhtarena.netty.simple
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/10 18:09
 */
public class NettyClient {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup =new NioEventLoopGroup();
        /**
         * 创建客户端对象
         * 注意客户端使用的不是ServerBootstrap，而是Bootstrap
         */
        Bootstrap bootstrap =new Bootstrap();
        try {
            bootstrap.group(eventLoopGroup) //设置线程组
                    .channel(NioSocketChannel.class) //设置客户端通道的实现类
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            sc.pipeline().addLast(new NettyClientHandler());
                        }
                    });

            System.out.println("客户端启动好了~ok");
            ChannelFuture channelFuture =bootstrap.connect("127.0.0.1",6668).sync();
            /**
             * 给关闭通道进行关闭
             */
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }

    }
}
