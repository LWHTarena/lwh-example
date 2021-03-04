package com.lwhtarena.conver;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * @author lwh
 * @folder com.lwhtarena.conver
 * @date 2016/10/5.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class Conver01 {

    public static void main(String[] args) {
        String str ="中国";
        byte[] data =str.getBytes();
        //字节数不完整
        System.out.println(new String(data,0,3));
    }

    @Test
    public void test01() throws UnsupportedEncodingException {
        //解码 byte --> char
        String str ="中国";//gbk

        //编码 char --> byte
        byte[] data =str.getBytes();
        //解码与解码字符集统一
        System.out.println(new String(data));
        data =str.getBytes("gbk");//设定编码字符集

        //不同一出现乱码
        System.out.println(new String(data));

        //编码
        byte[] data2 ="中国".getBytes("utf-8");
        //解码
        str =new String(data2,"utf-8");
        System.out.println(str);
    }
}
