package com.lwhtarena.nio.zerocopy;

import java.io.*;
import java.net.Socket;

/**
 * @author liwh
 * @Title: OldIOClient
 * @Package com.lwhtarena.nio.zerocopy
 * @Description: 传统io复制客户端
 *      输入流要被读，而输出流要往里写。这一点一定要记住。
 * @Version 1.0.0
 * @date 2020/6/1 14:44
 */
public class OldIOClient {
    public static void main(String[] args) throws IOException {
        Socket socket =new Socket("localhost",7001);
        String filename ="file.txt";
        InputStream inputStream =new FileInputStream(filename);

        DataOutputStream dataOutputStream =new DataOutputStream(socket.getOutputStream());

        byte[] buffer =new byte[4096];
        long readCount;
        long total =0;
        long startime =System.currentTimeMillis();
        while ((readCount =inputStream.read(buffer))>0){
            total +=readCount;
            dataOutputStream.write(buffer);
        }
        System.out.println("发送字节数："+total+",耗时"+(System.currentTimeMillis()-startime));
        dataOutputStream.close();
        socket.close();
        inputStream.close();
    }
}
