package com.lwhtarena.netty.codec2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

/**
 * @author liwh
 * @Title: NettyClient
 * @Package com.lwhtarena.netty.codec
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/14 16:20
 */
public class NettyClient {
    public static void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast("encoder",new ProtobufEncoder());
                            pipeline.addLast(new NettyClientHandler());
                        }
                    });
            System.out.println("客户端 启动ok~~");
            ChannelFuture channelFuture = bootstrap.connect("localhost", 6668).sync();
            channelFuture.channel().closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
        }
    }
}
