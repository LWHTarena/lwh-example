package com.lwhtarena.nio;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

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

    /**
     * 只读buffer
     */
    @Test
    public void test05(){
        ByteBuffer byteBuffer =ByteBuffer.allocate(64);
        for(int i=0;i<byteBuffer.capacity();i++){
            byteBuffer.put((byte) i);
        }

        byteBuffer.flip();

        /***得到只读buffer*/
        ByteBuffer readonlyBuffer = byteBuffer.asReadOnlyBuffer();
        log.info("{}",readonlyBuffer.getClass());

        //读取
        while (readonlyBuffer.hasRemaining()){
            log.info("=={}",readonlyBuffer.get());
        }

        readonlyBuffer.put((byte) 100); //抛出 ReadOnlyBufferException
    }

    /**
     * buffer 的put - get
     */
    @Test
    public void test06(){
        ByteBuffer buffer =ByteBuffer.allocate(64);

        /**
         * 类型化方式放入数据
         */
        buffer.putInt(100);
        buffer.putLong(9);
        buffer.putChar('您');
        buffer.putShort((short) 5);

        buffer.flip();

        log.info("{}",buffer.getInt());
        log.info("{}",buffer.getLong());
        log.info("{}",buffer.getChar());
        log.info("{}",buffer.getShort());

    }

    /**
     *
     * MappedByteBuffer 可让文件直接在内存（堆内存）修改，操作系统不需要拷贝一次
     *
     */
    @Test
    public void test07() throws IOException {
        RandomAccessFile randomAccessFile =new RandomAccessFile("file.txt","rw");

        /**获取对应的通道**/
        FileChannel channel =randomAccessFile.getChannel();

        /**
         * 参数1: FileChannel.MapMode.READ_WRITE 使用的读写模式
         * 参数2： 0 ： 可以直接修改的起始位置
         * 参数3:  5: 是映射到内存的大小(不是索引位置) ,即将 1.txt 的多少个字节映射到内存
         * 可以直接修改的范围就是 0-5
         * 实际类型 DirectByteBuffer
         */
        MappedByteBuffer mappedByteBuffer =channel.map(FileChannel.MapMode.READ_WRITE,0,5);

        mappedByteBuffer.put(0, (byte) 'K');
        mappedByteBuffer.put(3, (byte) '9');
//        mappedByteBuffer.put(5, (byte) 'Y'); //越界，IndexOutOfBoundsException

        randomAccessFile.close();
        log.info("修改成功...");
    }

    /**
     * Scattering：将数据写入到buffer时，可以采用buffer数组，依次写入  [分散]
     * Gathering: 从buffer读取数据时，可以采用buffer数组，依次读
     */
    @Test
    public void test08() throws IOException {
        /**使用 ServerSocketChannel 和 SocketChannel 网络*/
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

        //绑定端口到socket ，并启动
        serverSocketChannel.socket().bind(inetSocketAddress);

        //创建buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //等客户端连接(telnet)
        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 8;   //假定从客户端接收8个字节
        //循环的读取
        while (true) {
            int byteRead = 0;

            while (byteRead < messageLength ) {
                long l = socketChannel.read(byteBuffers);
                byteRead += l; //累计读取的字节数
                System.out.println("byteRead=" + byteRead);
                //使用流打印, 看看当前的这个buffer的position 和 limit
                Arrays.asList(byteBuffers).stream().map(buffer -> "postion=" + buffer.position() + ", limit=" + buffer.limit()).forEach(System.out::println);
            }

            //将所有的buffer进行flip
            Arrays.asList(byteBuffers).forEach(buffer -> buffer.flip());

            //将数据读出显示到客户端
            long byteWirte = 0;
            while (byteWirte < messageLength) {
                long l = socketChannel.write(byteBuffers); //
                byteWirte += l;
            }

            //将所有的buffer 进行clear
            Arrays.asList(byteBuffers).forEach(buffer-> {
                buffer.clear();
            });

            System.out.println("byteRead:=" + byteRead + " byteWrite=" + byteWirte + ", messagelength" + messageLength);
        }
    }
}



