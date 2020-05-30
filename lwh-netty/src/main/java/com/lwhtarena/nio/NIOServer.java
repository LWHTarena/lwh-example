package com.lwhtarena.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author liwh
 * @Title: NIOServer
 * @Package com.lwhtarena.nio
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/30 23:24
 */
@Slf4j
public class NIOServer {
    public static void main(String[] args) throws IOException {

        /**创建ServerSocketChannel**/
        ServerSocketChannel serverSocketChannel =ServerSocketChannel.open();

        /**得到一个Selector对象**/
        Selector selector =Selector.open();

        /**绑定一个端口6666，在服务器端监听**/
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        /**设置为非阻塞**/
        serverSocketChannel.configureBlocking(false);

        /**把serverSocketChannel注册到selector中，关心事件为OP_ACCEPT**/
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        /**循环等待客户端连接**/
        while (true){
            /**这里我们等待1秒后，没有事件发生，就继续**/
            if(selector.select(1000)==0){
                log.info("服务器等待了1秒，无连接");
                continue;
            }

            /**如果返回的>0,就获取相关的selectionKey集合
             * 1、如果返回的>0，表示已经获取得到关注事件
             * 2、selector.selectedKeys()返回关注事件的集合
             * **/
            Set<SelectionKey> selectionKeys =selector.selectedKeys();

            Iterator<SelectionKey> keyIterator =selectionKeys.iterator();
            while (keyIterator.hasNext()){
                SelectionKey key =keyIterator.next();
                if(key.isAcceptable()){
                    SocketChannel socketChannel =serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    /**将socketChannel注册到selector**/
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }

                if (key.isReadable()){
                    /**通过key反向获取对应的channel**/
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    /**获取到该channel关联的buffer**/
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    socketChannel.read(buffer);
                    log.info("from客户端：{}",new String(buffer.array()));
                }

                /**手动从集合中移动当前的selectionkey，防止重复操作**/
                keyIterator.remove();
            }

        }

    }
}
