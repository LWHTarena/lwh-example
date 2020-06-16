package com.lwhtarena.netty.protocoltcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author liwh
 * @Title: TcpServerInitializer
 * @Package com.lwhtarena.netty.protocoltcp
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/15 16:17
 */
public class TcpServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        pipeline.addLast(new TcpMessageDecoder()); //解码器
        pipeline.addLast(new TcpMessageEecoder()); //编码器
        pipeline.addLast(new TcpServerHandler());
    }
}
