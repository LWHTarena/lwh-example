package com.lwhtarena.注解;

/**
 * @author lwh
 * @folder com.lwhtarena.注解
 * @date 2016/10/6.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class TestAnnotation {

    public static void main(String[] args) {
        A a =new A();
        a.method02();

        String str ="abc";
    }
}

@HelloAnnotation(name = "liwenhao.com")
class A{

    @HelloAnnotation
    void method(){}

    @Deprecated
    void method02(){}
}


class B extends A{

    @Override
    void method(){}
}