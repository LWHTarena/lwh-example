package com.lwhtarena.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author：liwenhao
 * @Date:2017/2/11
 * @description:<p></p>
 * @version:v1.0
 */
public class TestRandomAccessFile {

    public static void main(String[] args) {
        try {
            /**
             * 创建一个随机访问文件流读
             * **/
            RandomAccessFile raf = new RandomAccessFile(new File("C:/WorkSpaces/idea/基础/07 IO流/src/com/lwhtarena/file/temp/liyuz.jpg"), "r");

            raf.seek(13); //从13开始读取

            //定义缓冲的大小
            byte[] flush =new byte[1024];

            //接收长度
            int len =0;
            len =raf.read(flush);
            while (-1!=len){
                System.out.println(new String(flush,0,len));
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
