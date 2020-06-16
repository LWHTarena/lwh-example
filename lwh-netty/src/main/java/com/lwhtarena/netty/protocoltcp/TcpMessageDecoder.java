package com.lwhtarena.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author liwh
 * @Title: TcpMessageDecoder
 * @Package com.lwhtarena.netty.protocoltcp
 * @Description: 解码器
 * @Version 1.0.0
 * @date 2020/6/15 16:50
 */
public class TcpMessageDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> list) throws Exception {
        System.out.println("TcpMessageDecoder 被调用");

        /**需要将得到二进制字节码 -> MessageProtocol数据包（对象）**/
        int length =in.readInt();
        byte[] content =new byte[length];
        in.readBytes(content);

        /**封装成MessageProtocol对象，放入list，传递下一个handler业务处理**/
        MessageProtocol messageProtocol =new MessageProtocol();
        messageProtocol.setLen(length);
        messageProtocol.setContent(content);
        list.add(messageProtocol);
    }
}
