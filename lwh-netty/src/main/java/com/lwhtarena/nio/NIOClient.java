package com.lwhtarena.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author liwh
 * @Title: NIOClient
 * @Package com.lwhtarena.nio
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/30 23:24
 */
@Slf4j
public class NIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel =SocketChannel.open();
        socketChannel.configureBlocking(false);

        InetSocketAddress inetSocketAddress =new InetSocketAddress("127.0.0.1",6666);
        if(!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                log.warn("因为连接需要事件，客户端不会阻塞，可以做其他工作...");
            }
        }

        String str ="hello,netty源码学习...";

        ByteBuffer buffer =ByteBuffer.wrap(str.getBytes());
        socketChannel.write(buffer);
        System.in.read();
    }
}
