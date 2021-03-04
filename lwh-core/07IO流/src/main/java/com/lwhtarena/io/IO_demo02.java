package com.lwhtarena.io;

/**
 * @author lwh
 * @folder com.lwhtarena.io
 * @date 2016/10/4.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */

import java.io.File;

/**
 * 相对路径与绝对路径构造File对象
 * 1、相对路径
 *  File(String parent,String child)  ==> File("E:/user/test","001.jpg");
 *  File(File parent,String child) ==> File(new File("E:/user/test"),"001.jpg");
 *
 * 2、绝对路径
 *  File(String name)
 */
public class IO_demo02 {

    public static void main(String[] args) {
        String parentPath ="D:\\IntellijSpace\\基础\\07 IO流\\temp\\io.txt";
        String name ="001.jpg";

        //相对路径
        File src =new File(parentPath,name);
        src =new File(new File(parentPath),name);

        //输出
        System.out.println(src.getName());
        System.out.println(src.getPath());

        //绝对路径
        src =new File("E:/xp/test/2.jpg");
        System.out.println(src.getName());
        System.out.println(src.getPath());

        //没有盘符: 以 user.dir构建
        src =new File("test.txt");
        src =new File(".");
        System.out.println(src.getName());
        System.out.println(src.getPath());
        System.out.println(src.getAbsolutePath());

    }
}
