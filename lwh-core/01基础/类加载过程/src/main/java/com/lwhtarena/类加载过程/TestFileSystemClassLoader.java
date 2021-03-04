package com.lwhtarena.类加载过程;

/**
 * 测试自定义的FileSystemClassLoader
 * @author lwh
 * @folder com.lwhtarena.类加载过程
 * @date 2016/10/9.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class TestFileSystemClassLoader {
    public static void main(String[] args) throws ClassNotFoundException {
        FileSystemClassLoader loader = new FileSystemClassLoader("D:\\IntellijSpace\\基础\\01 环境基础\\src\\com\\lwhtarena\\类加载过程");
        FileSystemClassLoader loader2 = new FileSystemClassLoader("D:\\IntellijSpace\\基础\\01 环境基础\\src\\com\\lwhtarena\\类加载过程");

        Class<?> c = loader.loadClass("com.lwhtarena.类加载过程");
        Class<?> c2 = loader.loadClass("com.lwhtarena.类加载过程");
        Class<?> c3 = loader2.loadClass("com.lwhtarena.类加载过程");

        Class<?> c4 = loader2.loadClass("java.lang.String");
        Class<?> c5 = loader2.loadClass("com.lwhtarena.类加载过程");


        System.out.println(c.hashCode());
        System.out.println(c2.hashCode());
        System.out.println(c3.hashCode());	//同一个类，被不同的加载器加载，JVM认为也是不相同的类
        System.out.println(c4.hashCode());
        System.out.println(c4.getClassLoader());	//引导类加载器
        System.out.println(c3.getClassLoader());	//自定义的类加载器
        System.out.println(c5.getClassLoader());	//系统默认的类加载器
    }
}
