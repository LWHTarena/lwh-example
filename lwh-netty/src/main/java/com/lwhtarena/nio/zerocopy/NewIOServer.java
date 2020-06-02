package com.lwhtarena.nio.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author liwh
 * @Title: NewIOServer
 * @Package com.lwhtarena.nio.zerocopy
 * @Description: nio io服务器
 * @Version 1.0.0
 * @date 2020/6/1 14:54
 */
public class NewIOServer {
    public static void main(String[] args) throws IOException {
        InetSocketAddress inetSocketAddress =new InetSocketAddress(7001);
        ServerSocketChannel serverSocketChannel =ServerSocketChannel.open();
        ServerSocket serverSocket =serverSocketChannel.socket();
        serverSocket.bind(inetSocketAddress);

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        while (true){
            SocketChannel socketChannel =serverSocketChannel.accept();
            int readCount =0;
            while (-1 !=readCount){
                try {
                    readCount =socketChannel.read(byteBuffer);
                }catch (Exception e){
                    break;
                }
            }
            /**倒带，position=0 mark作废**/
            byteBuffer.rewind();
        }

    }
}
