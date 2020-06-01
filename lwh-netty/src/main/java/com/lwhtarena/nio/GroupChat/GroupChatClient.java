package com.lwhtarena.nio.GroupChat;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author liwh
 * @Title: GroupChatClient
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
 * @date 2020/5/31 18:54
 */
@Slf4j
public class GroupChatClient {
    private final String HOST ="127.0.0.1";
    private final int PORT =6667;
    private SocketChannel socketChannel;
    private Selector selector;
    private String username;

    public GroupChatClient() throws IOException {
        selector = Selector.open();
        socketChannel =SocketChannel.open(new InetSocketAddress(HOST,PORT));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);

        username =socketChannel.getLocalAddress().toString().substring(1);
        log.info("{} is ok ...",username);
    }

    /**读取从服务器端回复消息**/
    private void readInfo() {
        try {
            int read =selector.select();
            if(read>0){
                /**有可以用的通道**/
                Iterator<SelectionKey> iterator =selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey key =iterator.next();
                    if(key.isReadable()){
                        SocketChannel sc = (SocketChannel) key.channel();
                        ByteBuffer buffer =ByteBuffer.allocate(1024);
                        sc.read(buffer);
                        String msg =new String(buffer.array());
                        log.info(msg.trim());
                    }
                }
                iterator.remove();
            }else{
                log.info("没有可用的通道...");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**向服务器发送消息**/
    private void sendInfo(String info) {
        info =username +" 说: "+info;
        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        GroupChatClient chatClient =new GroupChatClient();
        new Thread(()->{
            while (true){
                chatClient.readInfo();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Scanner scanner =new Scanner(System.in);
        while (scanner.hasNextLine()){
            String s =scanner.nextLine();
            chatClient.sendInfo(s);
        }
    }

}
