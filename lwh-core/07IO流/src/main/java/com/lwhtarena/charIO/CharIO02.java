package com.lwhtarena.charIO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 写文件
 * @author lwh
 * @folder com.lwhtarena.charIO
 * @date 2016/10/5.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class CharIO02 {
    public static void main(String[] args) {
        //创建源
        File dest = new File("D:\\IntellijSpace\\基础\\07 IO流\\temp\\mm.txt");

        //选择流
        Writer writer =null;
        try {
            writer =new FileWriter(dest,true);//追加文件，而不是覆盖文件
//            writer =new FileWriter(dest,false);//覆盖
            //写出
            String msg ="追加.....锄禾日当午\r\n码农真辛苦\r\n一本小破书\r\n一读一上午";
            writer.write(msg);
            writer.append("倒萨发了看电视剧 ok!");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (null != writer) {
                    writer.close();
                }
            } catch (Exception e2) {
            }
        }
    }
}
