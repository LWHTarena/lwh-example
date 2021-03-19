package com.lwhtarena.动态代理.jdk;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/19 22:51:01
 * @description 目标类：被代理类，即目标类target
 */
public class AService implements Service{
    @Override
    public void add() {
        System.out.println("AService add------>>>>");
    }

    @Override
    public void update() {
        System.out.println("AService update------>>>>");
    }
}
