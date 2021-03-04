package com.lwhtarena.byteIO;

/**
 * @author lwh
 * @folder com.lwhtarena.byteIO
 * @date 2016/10/4.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */

import java.io.*;

/**
 * 写出文件
 * 1、建立联系 file对象 目的地
 * 2、选择流 文件输出流  OutputStream FileOutputStream
 * 3、操作：write +flush
 * 4、释放资源
 */
public class ByteIO02 {
    public static void main(String[] args) {
        //建立联系  File对象  目的地
        File src =new File("D:\\IntellijSpace\\基础\\07 IO流\\temp\\io.txt");

        //选择流  文件输出流  OutputStream FileOutputStream
        OutputStream os =null;
        try {
//            os =new FileOutputStream(src,true);//追加
            os =new FileOutputStream(src,false);//覆盖
            //操作
            String str ="李文浩 努力工作，努力找女朋友 \r\n";
            //字符串转字节数组
            byte[] data =str.getBytes();
            os.write(data,0,data.length);
            os.flush();//强制刷新出去

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件未找到");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件写出失败");
        }finally {
            //4、释放资源 :关闭
            try {
                if (null != os) {
                    os.close();
                }
            } catch (Exception e2) {
                System.out.println("关闭输出流失败");
            }
        }

    }
}
