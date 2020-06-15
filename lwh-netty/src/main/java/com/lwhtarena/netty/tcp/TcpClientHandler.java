package com.lwhtarena.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

/**
 * @author liwh
 * @Title: TcpClientHandler
 * @Package com.lwhtarena.netty.tcp
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/15 16:36
 */
public class TcpClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /**使用客户端发送10条数据hello,server编号**/
        for(int i=0;i<10;i++){
            ByteBuf buf = Unpooled.copiedBuffer("hello,server~~"+i,Charset.forName("utf-8"));
            ctx.writeAndFlush(buf);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] buffer =new byte[msg.readableBytes()];
        msg.readBytes(buffer);

        String message =new String(buffer, Charset.forName("utf-8"));
        System.out.println("客户端接收到数据:"+message);
        System.out.println("客户端接收到消息量："+(++ this.count));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       cause.printStackTrace();
       ctx.close();
    }
}
