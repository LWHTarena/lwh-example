package com.lwhtarena.nio.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author liwh
 * @Title: NewIOClient
 * @Package com.lwhtarena.nio.zerocopy
 * @Description: nio 客户端
 * @Version 1.0.0
 * @date 2020/6/1 14:59
 */
public class NewIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel =SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",7001));
        String filename ="file.txt";

        FileChannel fileChannel =new FileInputStream(filename).getChannel();

        long startime =System.currentTimeMillis();

        /**
         * 在linux下一个transferTo 方法就可以完成传输
         * 在windows下一次调用transferTo 只能发送8m，就需要分段传输文件，而且要主要传输的位置
         * transferTo 底层使用到零拷贝
         */
        long transferCount =fileChannel.transferTo(0,fileChannel.size(),socketChannel);
        System.out.println("发送字节数："+transferCount+",耗时"+(System.currentTimeMillis()-startime));
        fileChannel.close();
    }
}
