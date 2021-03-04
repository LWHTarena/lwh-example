package com.lwhtarena.buffered;

import java.io.*;

/**
 * 字符缓冲流 +新增方法(不能发生多态)
 * @author lwh
 * @folder com.lwhtarena.buffered
 * @date 2016/10/5.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class bufferedIO02 {
    public static void main(String[] args) {
        //创建源 仅限于 字符的纯文本
        File src =new File("D:\\IntellijSpace\\基础\\07 IO流\\temp\\buffered.txt");
        File dest =new File("D:\\IntellijSpace\\基础\\07 IO流\\temp\\bufferedcopy.txt");

        //选择流
        BufferedReader bfr =null;
        BufferedWriter bfw =null;
        try {
            bfr =new BufferedReader(new FileReader(src));
            bfw =new BufferedWriter(new FileWriter(dest));

            String line =null;
            while (null!=(line=bfr.readLine())){
                bfw.write(line);
                bfw.newLine();//换行符号
            }
            bfw.flush();//强制刷出
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("源文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件读取失败");
        }finally {
            try {
                if (null != bfw) {
                    bfw.close();
                }
            } catch (Exception e2) {
            }
            try {
                if (null != bfr) {
                    bfr.close();
                }
            } catch (Exception e2) {
            }
        }
    }
}
