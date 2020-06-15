package com.lwhtarena.netty.tcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author liwh
 * @Title: TcpClinetInitialLizer
 * @Package com.lwhtarena.netty.tcp
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/15 16:33
 */
public class TcpClinetInitialLizer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        pipeline.addLast(new TcpClientHandler());
    }
}
