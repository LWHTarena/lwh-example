package com.lwhtarena.dubborpc.netty;

import com.lwhtarena.dubborpc.custom.ClientBootstrap;
import com.lwhtarena.dubborpc.provider.HelloServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author liwh
 * @Title: NettyServerHandler
 * @Package com.lwhtarena.dubborpc.netty
 * @Description: 服务器这边的handler
 * @Version 1.0.0
 * @date 2020/6/16 03:36
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("msg:"+msg);
        /**
         * 客户端在调用服务的api时，我们需要定义一个协议
         */
        if(msg.toString().startsWith(ClientBootstrap.providerName)){
            String result = new HelloServiceImpl().hello(msg.toString().substring(msg.toString().lastIndexOf("#") + 1));
            ctx.writeAndFlush(result);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
