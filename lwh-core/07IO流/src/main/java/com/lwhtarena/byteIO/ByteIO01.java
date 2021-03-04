package com.lwhtarena.byteIO;

/**
 * @author lwh
 * @folder com.lwhtarena.byteIO
 * @date 2016/10/4.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */

import java.io.*;

/**
 * 文件的读取
 * 1、建立联系 File对象
 * 2、选择流 文件输入流 inputStream FileInputStream
 * 3、操作
 *     byte[] car =new byte[1024];  read 读取大小
 * 4、释放资源：关闭
 *
 */
public class ByteIO01 {
    public static void main(String[] args) {
        //建立联系 file对象
        File src =new File("D:\\IntellijSpace\\基础\\07 IO流\\temp\\byteio01.txt");
        //选择流
        InputStream is =null;
        try {
            is =new FileInputStream(src);
            //操作 不断读取，缓冲数组
            byte[] car =new byte[1024];
            int len =0; // 接收 实际读取大小

            //循环读取
            StringBuilder sb =new StringBuilder();
            while (-1!=(len=is.read(car))){
                //输出  字节数组转成字符串
                String info =new String(car,0,len);
                sb.append(info);
            }
            System.out.println(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件不存在");
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("读取文件失败");
        }finally {
            try {
                //4、释放资源
                if (null != is) {
                    is.close();
                }
            } catch (Exception e2) {
                System.out.println("关闭文件输入流失败");
            }
        }
    }
}
