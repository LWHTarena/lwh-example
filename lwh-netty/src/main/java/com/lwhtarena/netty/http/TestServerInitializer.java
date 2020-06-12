package com.lwhtarena.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author liwh
 * @Title: TestServerInitializer
 * @Package com.lwhtarena.netty.http
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/12 06:31
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //向管道加入处理器

        /*得到管道*/
        ChannelPipeline pipeline = socketChannel.pipeline();
        /*加入netty提供的httpservercodec   codec ->[coder decoder]*/
        //netty提供的HttpServerCodec --编 解码器
        pipeline.addLast("myhttpcoder",new HttpServerCodec());
        pipeline.addLast("myTestHttpServerHandler",new TestHttpServerHandler());

        System.out.println("~~~~~ ok ");
    }
}
