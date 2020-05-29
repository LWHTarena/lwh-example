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
 *
 * telnet *.*.*.* port ，然后crtl+]进入命令模式，使用send发送消息，如：send hello,murphy
 *
 * 常用命令：
 *
 * 　　open : 使用 openhostname 可以建立到主机的 Telnet 连接。
 *
 * 　　close : 使用命令 close 命令可以关闭现有的 Telnet 连接。
 *
 * 　　display : 使用 display 命令可以查看 Telnet 客户端的当前设置。
 *
 * 　　send : 使用 send 命令可以向 Telnet 服务器发送命令。支持以下命令：
 *
 * 　　ao : 放弃输出命令。
 *
 * 　　ayt : “Are you there”命令。
 *
 * 　　esc : 发送当前的转义字符。
 *
 * 　　ip : 中断进程命令。
 *
 * 　　synch : 执行 Telnet 同步操作。
 *
 * 　　brk : 发送信号。
 *
 * 　　上表所列命令以外的其他命令都将以字符串的形式发送至 Telnet 服务器。例如，sendabcd 将发送字符串 abcd 至 Telnet 服务器，这样，Telnet 会话窗口中将出现该字符串。
 *
 * 　　quit ：使用 quit 命令可以退出 Telnet 客户端。
 *
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
                if(read !=-1){
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
                10,10,10,TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
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
