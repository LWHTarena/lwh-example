package com.lwhtarena.netty.protocoltcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author liwh
 * @Title: TCPServer
 * @Package com.lwhtarena.netty.protocoltcp
 * @Description: tcp 的粘包拆包 解决方案
 *  1、使用自定义协议 + 编解码器 来解决
 *  2、关键就是要解决服务器端每次读取数据长度的问题，这个问题
 * 解决，就不会出现服务器多读或少读的问题，从而避免的TCP粘包、拆包
 *
 * @Version 1.0.0
 * @date 2020/6/15 16:11
 */
public class TCPServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup =new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new TcpServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(7008).sync();
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
