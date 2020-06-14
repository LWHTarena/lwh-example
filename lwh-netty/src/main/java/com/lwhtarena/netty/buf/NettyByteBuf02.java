package com.lwhtarena.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import java.nio.charset.Charset;

/**
 * @author liwh
 * @Title: NettyByteBuf02
 * @Package com.lwhtarena.netty.buf
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/13 07:34
 */
public class NettyByteBuf02 {
    public static void main(String[] args) {
        /**创建bytebuf**/
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello,world!", CharsetUtil.UTF_8);

        /**使用相关的方法**/
        if(byteBuf.hasArray()){
            byte[] content = byteBuf.array();

            /**将content转成字符串**/
            System.out.println(new String(content, Charset.forName("utf-8")));
            System.out.println("bytebuf="+byteBuf);

            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());

            System.out.println(byteBuf.getByte(0));

            int len =byteBuf.readableBytes();//可读取字节数
            System.out.println("len ="+len);

            for(int i=0;i<len;i++){
                System.out.println((char) byteBuf.getByte(i));
            }
            /**按照某个范围读取**/
            System.out.println(byteBuf.getCharSequence(0,4,Charset.forName("utf-8")));
            System.out.println(byteBuf.getCharSequence(4,6,Charset.forName("utf-8")));
        }


    }
}
