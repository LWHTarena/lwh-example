package com.lwhtarena.netty.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

/**
 * @author liwh
 * @Title: TcpClientHandler
 * @Package com.lwhtarena.netty.protocoltcp
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/15 16:36
 */
public class TcpClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /**使用客户端发送 今天天冷，吃火锅~~ 编号**/
        for(int i=0;i<5;i++){
            String ms ="今天天冷，吃火锅~~"+i;
            /**转化为字节数组**/
            byte[] content =ms.getBytes(Charset.forName("utf-8"));
            int length =content.length;

            /**创建协议包对象**/
            MessageProtocol mp =new MessageProtocol();
            mp.setLen(length);
            mp.setContent(content);
            ctx.writeAndFlush(mp);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       cause.printStackTrace();
       ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        int len =msg.getLen();
        byte[] content =msg.getContent();

        System.out.println("客户端接收到消息如下:");
        System.out.println("长度:"+len);
        System.out.println("内容："+new String(content,Charset.forName("utf-8")));
        System.out.println("客户端接收到消息量="+(++ this.count));
    }
}
