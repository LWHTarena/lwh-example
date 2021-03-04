package com.lwhtarena.io;

/**
 * @author lwh
 * @folder com.lwhtarena.io
 * @date 2016/10/4.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */

import java.io.File;

/**
 * 两个常量
 * 1、路径分割符
 * 2、文件分割符 \(window) 、 /(linux等)
 */
public class IO_demo01 {

    public static void main(String[] args) {
        System.out.println(File.pathSeparator); //;
        System.out.println(File.separator); //\

        //路径表示形式
        System.out.println("============路径表示形式===========");
        String path ="";
        path ="D:"+File.separator+"";

        //推荐方式


    }

}
