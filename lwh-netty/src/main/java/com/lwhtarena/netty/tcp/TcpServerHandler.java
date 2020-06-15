package com.lwhtarena.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * @author liwh
 * @Title: TcpServerHandler
 * @Package com.lwhtarena.netty.tcp
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/15 16:20
 */
public class TcpServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private int count;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] buffer = new byte[msg.readableBytes()];
        msg.readBytes(buffer);

        /**将buffer转成字符串**/
        String message =new String(buffer, Charset.forName("utf-8"));
        System.out.println("服务器接收到数据:"+message);
        System.out.println("服务器接收到消息量："+(++ this.count));

        /**服务器回送的数据给客户端，响应一个随机的id**/
        ByteBuf responseBuf =Unpooled.copiedBuffer(UUID.randomUUID().toString()+ " ",CharsetUtil.UTF_8);
        ctx.writeAndFlush(responseBuf);
    }
}
