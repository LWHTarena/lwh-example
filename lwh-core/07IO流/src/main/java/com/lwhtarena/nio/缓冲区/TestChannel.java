package com.lwhtarena.nio.缓冲区;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author：liwenhao
 * @Date:2017/2/9
 * @description:<p>
 *
 * 一、通道（Channel）：用于源节点与目标节点的连接。在 Java NIO 中负责缓冲区中数据的传输。Channel 本身不存储数据，因此需要配合缓冲区进行传输。
 *
 * 二、通道的主要实现类
 * 	java.nio.channels.Channel 接口：
 * 		|--FileChannel
 * 		|--SocketChannel
 * 		|--ServerSocketChannel
 * 		|--DatagramChannel
 *
 * 三、获取通道
 * 1. Java 针对支持通道的类提供了 getChannel() 方法
 * 		本地 IO：
 * 		FileInputStream/FileOutputStream
 * 		RandomAccessFile
 *
 * 		网络IO：
 * 		Socket
 * 		ServerSocket
 * 		DatagramSocket
 *
 * 2. 在 JDK 1.7 中的 NIO.2 针对各个通道提供了静态方法 open()
 * 3. 在 JDK 1.7 中的 NIO.2 的 Files 工具类的 newByteChannel()
 *
 * 四、通道之间的数据传输
 * transferFrom()
 * transferTo()
 *
 * 五、分散(Scatter)与聚集(Gather)
 * 分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中
 * 聚集写入（Gathering Writes）：将多个缓冲区中的数据聚集到通道中
 *
 * 六、字符集：Charset
 * 编码：字符串 -> 字节数组
 * 解码：字节数组  -> 字符串
 *
 * </p>
 * @version:v1.0
 */
public class TestChannel {

    /**
     *  利用通道完成文件的复制（非直接缓冲区）
     */
    @Test
    public void test01(){
        long start = System.currentTimeMillis();
        FileInputStream fis =null;
        FileOutputStream fos=null;

        FileChannel inChannel =null;
        FileChannel outChannel =null;

        try {
            fis =new FileInputStream("C:\\WorkSpaces\\idea\\基础\\14 NIO\\src\\main\\java\\com\\lwhtarena\\nio\\缓冲区\\temp\\it.jpg");
            fos =new FileOutputStream("C:\\WorkSpaces\\idea\\基础\\14 NIO\\src\\main\\java\\com\\lwhtarena\\nio\\缓冲区\\temp\\it_copy.jpg");

            //①获取通道
            inChannel =fis.getChannel();
            outChannel =fos.getChannel();

            //②分配指定大小的缓冲区
            ByteBuffer buf =ByteBuffer.allocate(1024);

            //③将通道中的数据存入缓冲区中
            while (inChannel.read(buf)!=-1){
                //切换读取数据的模式
                buf.flip();

                //④将缓冲区中的数据写入通道中
                outChannel.write(buf);
                buf.clear(); //清空缓冲区
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(outChannel!=null){
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inChannel!=null){
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费时间为：" + (end - start));
    }
}
