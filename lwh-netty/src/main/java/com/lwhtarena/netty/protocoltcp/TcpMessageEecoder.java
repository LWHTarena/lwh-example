package com.lwhtarena.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author liwh
 * @Title: TcpMessageEecoder
 * @Package com.lwhtarena.netty.protocoltcp
 * @Description: 编码器
 * @Version 1.0.0
 * @date 2020/6/15 16:50
 */
public class TcpMessageEecoder extends MessageToByteEncoder<MessageProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProtocol msg, ByteBuf out) throws Exception {
        System.out.println("TcpMessageEecoder 方法被调用");
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getContent());
    }
}
