package com.lwhtarena.nio;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author liwh
 * @Title: NIOFileChannel
 * @Package com.lwhtarena.nio
 * @Description: 本地文件读写
 *    输入流要被读，而输出流要往里写。这一点一定要记住。
 *
 * @Version 1.0.0
 * @date 2020/5/30 10:15
 */
@Slf4j
public class NIOFileChannel {

    /**
     * 写文件
     * @throws IOException
     */
    @Test
    public void test01() throws IOException {
        String str ="您好，netty";

        /**创建一个输出流->channel**/
        FileOutputStream fileOutputStream =new FileOutputStream("file.txt");

        /**
         * 通过fileOutputStream获取对应的FileChannel
         * 这个fileChannel真实类型是FileChannel
         * **/
        FileChannel fileChannel =fileOutputStream.getChannel();

        /**
         * 创建一个缓存区ByteBuffer
         */
        ByteBuffer byteBuffer =ByteBuffer.allocate(1024);

        /**将str放入byteBuffer**/
        byteBuffer.put(str.getBytes());

        /**切换byteBuffer进行flip**/
        byteBuffer.flip();

        /**将byteBuffer数据写入到fileChannel**/
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }

    /**
     * 读文件
     * @throws IOException
     */
    @Test
    public void test02() throws IOException {
        /**创建文件的输入流**/
        File file =new File("file.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        /**通过fileInputStream获取对应的FileChannel->实际类型 FileChannelImpl**/
        FileChannel fileChannel =fileInputStream.getChannel();

        /**创建缓冲区**/
        ByteBuffer byteBuffer =ByteBuffer.allocate((int) file.length());

        /**将通道的数据读入到buffer**/
        fileChannel.read(byteBuffer);

        /**将bytebuffer的字节数转成字符串**/
        log.info("字节数转为字符串:{}",new String(byteBuffer.array()));
        fileInputStream.close();
    }

    /**
     * 将文件的内容读取到另一个文件里
     * @throws IOException
     */
    @Test
    public void test03() throws IOException {
        FileInputStream fileInputStream =new FileInputStream("file.txt");
        FileChannel fileChannel =fileInputStream.getChannel();

        FileOutputStream fileOutputStream =new FileOutputStream("file2.txt");
        FileChannel fileChannel2 =fileOutputStream.getChannel();

        ByteBuffer byteBuffer =ByteBuffer.allocate(512);

        /**循环读取**/
        while (true){
            byteBuffer.clear(); /*清空buffer*/
            int read =fileChannel.read(byteBuffer);
            log.info("read={}",read);
            if(read == -1){
                break;
            }

            //将buffer中的数据写入到filechannel2
            byteBuffer.flip();
            fileChannel2.write(byteBuffer);
        }

        fileInputStream.close();
        fileOutputStream.close();
    }

    /**
     * 文件拷贝
     */
    @Test
    public void test04() throws IOException {
        /**创建流**/
        FileInputStream fileInputStream =new FileInputStream("a.jpg");
        FileOutputStream fileOutputStream =new FileOutputStream("b.jpg");

        /**获取对应流的channel**/
        FileChannel sourceCh =fileInputStream.getChannel();
        FileChannel destCh =fileOutputStream.getChannel();

        /**使用transferFrom来拷贝**/
        destCh.transferFrom(sourceCh,0,sourceCh.size());

        /**关闭流和通道**/
        sourceCh.close();
        destCh.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
