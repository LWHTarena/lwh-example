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
