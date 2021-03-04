package com.lwhtarena.conver;

import java.io.*;

/**
 * 转换流：字节转为字符
 * 1、输出流 OutputStreamWriter 编码
 * 2、输入流 InputStreamReader  解码
 * @author lwh
 * @folder com.lwhtarena.conver
 * @date 2016/10/5.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class Conver02 {
    public static void main(String[] args) {

        BufferedReader br =null;
        BufferedWriter bw =null;
        try {
            //指定解码字符集
             //BufferedReader br =new BufferedReader(new FileReader(new File("e:xxx.txt")));
             br=new BufferedReader(
                     new InputStreamReader(
                         new BufferedInputStream(
                             new FileInputStream(
                                 new File("D:\\IntellijSpace\\基础\\07 IO流\\src\\com\\lwhtarena\\conver\\Conver01.java","UTF-8")))));

            //写出文件 编码
             bw=new BufferedWriter(
                    new OutputStreamWriter(
                        new BufferedOutputStream(
                            new FileOutputStream(
                                new File("D:\\IntellijSpace\\基础\\07 IO流\\src\\com\\lwhtarena\\conver\\Conver01.java")))));

            String info =null;
            try {
                while (null!=(info =br.readLine())){
                    bw.write(info);
                    bw.newLine();
                }
                bw.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
