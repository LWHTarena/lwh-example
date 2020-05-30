package com.lwhtarena.nio;

import lombok.extern.slf4j.Slf4j;

import java.nio.IntBuffer;

/**
 * @author liwh
 * @Title: BasicBuffer
 * @Package com.lwhtarena.nio
 * @Description:
 *
 * 1）每个channel都会对应一个Buffer
 * 2）Selector 对应一个线程,一个线程对应多个channe(连接)
 * 3）该图反应了有n个 channel 注册到该 selecor /程序
 * 4）程序切换到哪个channel 是有事件决定的,Event 就是一个重要的概念
 * 5）Selector 会根据不同的事件,在各个通道上切换
 * 6) Buffer 就是一个内存块,底层是有一个数组
 * 7）数据的读取写入是通过Buffer,这个和BIO,BIO中要么是输入流,或者是输出流,不能双向,但是NIO的Buffer 是可
 * 以读也可以写,需要 flip 方法切换
 *   channel是双向的,可以返回底层操作系统的情况,比如Linux,底层的操作系统通道就是双向的.
 *
 * @Version 1.0.0
 * @date 2020/5/30 09:25
 */
@Slf4j
public class BasicBuffer {
    public static void main(String[] args) {
        /**
         * 举例说明buffer的使用（简单说明）
         * 创建一个buffer，大小为5，即可以存放在5个int
         */
        IntBuffer intBuffer = IntBuffer.allocate(5);

        /**向buffer存放数据**/
        for(int i=0;i<intBuffer.capacity();i++){
            intBuffer.put(i*2);
        }

        /**
         * 如何从buffer中读取数据
         * 读写切换（buffer转换）
         */
        intBuffer.flip();

        /**设置**/
//        intBuffer.position(1);
//        System.out.println(intBuffer.get());
//        intBuffer.limit(3);

        while (intBuffer.hasRemaining()){
            log.info("读取buffer:{}",intBuffer.get());
        }


    }
}
