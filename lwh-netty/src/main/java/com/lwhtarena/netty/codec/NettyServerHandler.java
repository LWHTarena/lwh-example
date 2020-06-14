package com.lwhtarena.netty.codec;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author liwh
 * @Title: NettyServerHandler
 * @Package com.lwhtarena.netty.codec
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/14 16:11
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<StudentPOJO.Student> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, StudentPOJO.Student msg) throws Exception {
        System.out.println("客户端发送的数据 id="+msg.getId()+" 名字="+msg.getName());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端~~~哈哈", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
