package com.lwhtarena.netty.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * @author liwh
 * @Title: TcpServerHandler
 * @Package com.lwhtarena.netty.protocoltcp
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/15 16:20
 */
public class TcpServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    private int count;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        int len =msg.getLen();
        byte[] content =msg.getContent();

        System.out.println("     ");
        System.out.println("     ");

        System.out.println("服务器接收到信息如下:");
        System.out.println("长度:"+len);
        System.out.println("内容:"+new String(content, Charset.forName("utf-8")));
        System.out.println("服务器接收到消息包量："+(++ this.count));

        /*响应消息*/
        /**服务器回送的数据给客户端，响应一个随机的id**/
        String uuid = UUID.randomUUID().toString();
        byte[] responseContent =uuid.getBytes("utf-8");
        int responseLen =responseContent.length;

        /**构建协议包**/
        MessageProtocol messageProtocol =new MessageProtocol();
        messageProtocol.setLen(responseLen);
        messageProtocol.setContent(responseContent);
        ctx.writeAndFlush(messageProtocol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
