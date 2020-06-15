package com.lwhtarena.netty.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author liwh
 * @Title: TCPClient
 * @Package com.lwhtarena.netty.tcp
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/15 16:30
 */
public class TCPClient {
    public static void main(String[] args) {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new TcpClinetInitialLizer());
            ChannelFuture future = bootstrap.connect("localhost", 7000).sync();
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            workGroup.shutdownGracefully();
        }
    }
}
