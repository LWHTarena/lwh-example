package com.lwhtarena.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liwh
 * @Title: BIOServer
 * @Package com.lwhtarena.bio
 * @Description: 传统IO编程
 * @Version 1.0.0
 * @date 2020/5/18 20:07
 */

@Slf4j
public class BIOServer {

    /**
     * 编写一个handler方法，和客户端通信
     * @param socket
     */
    public static void handler(Socket socket){
        log.info("线程信息id:{} ,名字={}",Thread.currentThread().getContextClassLoader(),Thread.currentThread().getName());
        try {
            byte[] bytes =new byte[1024];
            /**通过socker获取输入流**/
            InputStream inputStream =socket.getInputStream();

            while (true){
                log.info("线程信息id:{} ,名字={}",Thread.currentThread().getContextClassLoader(),Thread.currentThread().getName());
                log.info("......");
                int read =inputStream.read(bytes);
                if(read!=-1){
                    /**输出客户端发送的数据**/
                    log.info(new String(bytes,0,read));
                }else {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            log.info("关闭与clinet的连接");
            try {
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 服务器端，监听端口6666，当有客户连接时，就启动一个线程与之通信
     *
     * 服务器端可以接收客户端发送的数据（cmd telnet方式连接即可）
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

       /**
         * 线程池机制
         * 1、创建线程池
         * 2、如果有客户端连接，就创建一个线程，与之通讯
         */
        //构造一个线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                1,1,10,TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(1),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        /**创建serverSocker**/
        ServerSocket serverSocket =new ServerSocket(6666);

        log.info("服务器启动了");

        while (true){
            log.info("线程信息id:{} ,名字={}",Thread.currentThread().getContextClassLoader(),Thread.currentThread().getName());

            /**监听，等待客户连接**/
            final Socket socket =serverSocket.accept();
            log.info("连接到一个客户...");

            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    handler(socket);
                }
            });
        }

    }
}
