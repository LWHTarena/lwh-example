package com.lwhtarena.类加载过程;

/**
 * 测试类加载器的层次结构、双亲委托机制
 * @author lwh
 * @folder com.lwhtarena.类加载过程
 * @date 2016/10/9.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class TestClassLoader {

    public static void main(String[] args) {
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());   //JAVA_HOME/jre/lib/rt.jar
        System.out.println(System.getProperty("java.class.path"));

        System.out.println("################");
        String a = "gaogao";
        System.out.println(a.getClass().getClassLoader());
        System.out.println(a);
    }
}
