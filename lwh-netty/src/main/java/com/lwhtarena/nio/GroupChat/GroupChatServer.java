package com.lwhtarena.nio.GroupChat;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author liwh
 * @Title: GroupChatServer
 * @Package com.lwhtarena.nio.GroupChat
 * @Description:
 * <pre>
 *     SocketChannel与ServerSocketChannel区别:
 *     （1）Socket 和ServerSocke 是一对 他们是java.net下面实现socket通信的类
 *     （2）SocketChannel 和ServerSocketChannel是一对 他们是java.nio下面实现通信的类 支持异步通信
 *     （3）服务器必须先建立ServerSocket或者ServerSocketChannel 来等待客户端的连接
 *     （4）客户端必须建立相对应的Socket或者SocketChannel来与服务器建立连接
 *     （5）服务器接受到客户端的连接受，再生成一个Socket或者SocketChannel与此客户端通信
 * </pre>
 * @Version 1.0.0
 * @date 2020/5/31 18:53
 */
@Slf4j
public class GroupChatServer {

    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT =6667;

    /**
     * 监听
     */
    private void listen() {
        log.info("监听线程:{}",Thread.currentThread().getName());
        try {
            /**循环监听**/
            while (true){
                int count =selector.select();
                if(count>0){
                    /**有事件处理**/
                    Iterator<SelectionKey> selectionKeyIterator =selector.selectedKeys().iterator();
                    while (selectionKeyIterator.hasNext()){
                        SelectionKey key =selectionKeyIterator.next();

                        /**监听到accept**/
                        if(key.isAcceptable()){
                            SocketChannel sc =listenChannel.accept();
                            sc.configureBlocking(false);

                            /**将该sc注册到selector**/
                            sc.register(selector,SelectionKey.OP_READ);

                            log.info("服务器端监听到{}上线",sc.getRemoteAddress());
                        }

                        /**通发送read事件，即通道是可读的状态**/
                        if(key.isReadable()){
                            readData(key);
                        }
                        /**当前的key删除，防止重复处理**/
                        selectionKeyIterator.remove();
                    }
                }else{
                    log.info("等待...");
                }
            }
        }catch (Exception e){
            log.error("抛异常:{}",e);
        }finally {
            //发生异常处理
        }
    }

    /**
     * 读取客户端信息
     * **/
    private void readData(SelectionKey key) {
        SocketChannel socketChannel =null;
        try {
            socketChannel = (SocketChannel) key.channel();
            ByteBuffer buffer =ByteBuffer.allocate(1024);

            int count = socketChannel.read(buffer);
            if(count>0){
                /**把缓存区的数据转换成字符串**/
                String msg =new String(buffer.array());
                log.info("from客户端:{}",msg);

                /**向其他的客户端转发消息（去掉自己），专门写一个方法处理**/
                sendInfoToOtherClient(msg,socketChannel);
            }

        }catch (Exception e){
            try {
                log.info("{}离线了",socketChannel.getRemoteAddress());
                /**取消注册**/
                key.cancel();
                socketChannel.close();
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }

    /**
     * 转发消息给其他客户（通道）
     * **/
    private void sendInfoToOtherClient(String msg, SocketChannel self) throws IOException {
        log.info("服务器转发消息中...");
        log.info("服务器转发数据给客户端线程：{}",Thread.currentThread().getName());
        for(SelectionKey key: selector.keys()){
            /**通过key取出对应的socketChannel**/
            Channel targetChannel =key.channel();
            if(targetChannel instanceof SocketChannel && targetChannel!=self){
                SocketChannel dest = (SocketChannel) targetChannel;
                ByteBuffer buffer =ByteBuffer.wrap(msg.getBytes());

                /**将buffer的数据写到通道**/
                dest.write(buffer);
            }
        }
    }

    /**初始化工作**/
    public GroupChatServer() {
        try {
            /**得到selector**/
            selector = Selector.open();
            listenChannel =ServerSocketChannel.open();

            listenChannel.socket().bind(new InetSocketAddress(PORT));
            listenChannel.configureBlocking(false);
            listenChannel.register(selector,SelectionKey.OP_ACCEPT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer =new GroupChatServer();
        groupChatServer.listen();
    }

}
