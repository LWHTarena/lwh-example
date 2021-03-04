package com.lwhtarena.charIO;

import java.io.*;

/**
 * 纯文本读取
 * @author lwh
 * @folder com.lwhtarena.charIO
 * @date 2016/10/5.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class CharIO01 {

    public static void main(String[] args) {
        //创建源
        File src =new File("D:\\IntellijSpace\\基础\\07 IO流\\temp\\charIO.txt");

        //选择流
        Reader reader =null;

        try {
            reader = new FileReader(src);

            //读写操作
            char[] flush =new char[1024];
            int len =0;
            while(-1!=(len=reader.read(flush))){
                //字符数组转成字符串
                String str =new String(flush,0,len);
                System.out.println(str);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("源文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件读取失败");
        }finally {
            try {
                if (null != reader) {
                    reader.close();
                }
            } catch (Exception e2) {
            }
        }

    }
}
