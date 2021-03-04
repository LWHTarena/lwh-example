package com.lwhtarena.charIO;

import java.io.*;

/**
 * 纯文本拷贝
 * @author lwh
 * @folder com.lwhtarena.charIO
 * @date 2016/10/5.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class CopyFile {
    public static void main(String[] args) {
        //创建源 仅限于 字符的纯文本
        File src =new File("D:\\IntellijSpace\\基础\\07 IO流\\temp\\charIO.txt");
        File dest =new File("D:\\IntellijSpace\\基础\\07 IO流\\temp\\copyCharIO.txt");

        //选择流
        Reader reader =null;
        Writer writer =null;

        try {
            reader =new FileReader(src);
            writer =new FileWriter(dest);

            //读写操作
            char[] flush =new char[1024];
            int len =0;
            while (-1!=(len=reader.read(flush))){
                writer.write(flush,0,len);
            }
            writer.flush();//强制刷出

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("源文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件读取失败");
        }finally {
            try {
                if (null != writer) {
                    writer.close();
                }
            } catch (Exception e2) {
            }
            try {
                if (null != reader) {
                    reader.close();
                }
            } catch (Exception e2) {
            }
        }
    }
}
