package com.lwhtarena.动态代理.cglib;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/19 23:01:25
 * @description 目标类，被代理类
 */
public class Base {
    public void add(){
        System.out.println("add()---------------------");
    }
}
