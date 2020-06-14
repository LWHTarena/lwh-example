package com.lwhtarena.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author liwh
 * @Title: NettyByteBuf01
 * @Package com.lwhtarena.netty.buf
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/13 07:25
 */
public class NettyByteBuf01 {
    public static void main(String[] args) {
        /**
         * 创建一个bytebuf
         * 1、创建对象，该对象包含一个数组arr，是一个byte[10]
         * 2、在netty的buffer中，不需要使用flip进行反转
         *  底层维护了，不需要使用writeindex和readindex
         * 3、
         */
        ByteBuf buf = Unpooled.buffer(10);
        for(int i=0;i<10;i++){
            buf.writeByte(i);
        }
        System.out.println("capacity="+buf.capacity());

        //输出
        for(int i=0;i<buf.capacity();i++){
            System.out.println(buf.readByte());
        }

        System.out.println("执行完毕~");
    }
}
