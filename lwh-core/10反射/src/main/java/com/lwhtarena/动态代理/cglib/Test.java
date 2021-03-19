package com.lwhtarena.动态代理.cglib;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/19 23:15:14
 * @description
 */
public class Test {
    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        Base base = Factory.getInstance(proxy);
        base.add();
    }
}
