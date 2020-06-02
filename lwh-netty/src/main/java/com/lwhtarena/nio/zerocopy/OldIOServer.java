package com.lwhtarena.nio.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The type Old io server.
 *
 * @author liwh
 * @Title: OldIOServer
 * @Package com.lwhtarena.nio.zerocopy
 * @Description: java io 服务器
 * @Version 1.0.0
 * @date 2020 /6/1 14:39
 */
public class OldIOServer {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket =new ServerSocket(7001);
        while (true){
            Socket socket =serverSocket.accept();
            DataInputStream dataInputStream =new DataInputStream(socket.getInputStream());

            try {
                byte[] byteArray =new byte[4096];
                while (true){
                    int readCount =dataInputStream.read(byteArray,0,byteArray.length);
                    if(-1 == readCount){
                        break;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
