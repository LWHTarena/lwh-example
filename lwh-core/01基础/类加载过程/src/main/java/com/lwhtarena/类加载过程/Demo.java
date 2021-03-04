package com.lwhtarena.类加载过程;

/**
 * @author lwh
 * @folder com.lwhtarena.类加载过程
 * @date 2016/10/9.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class Demo {
    public static void main(String[] args) {

        A a =new A();
        System.out.println(A.width);
    }
}


class A{
    public static int width =100;

    static {
        System.out.println("静态初始化类A");
    }

    public A(){
        System.out.println("创建A类的对象");
    }

}