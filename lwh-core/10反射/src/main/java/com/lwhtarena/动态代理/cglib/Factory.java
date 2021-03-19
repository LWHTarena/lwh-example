package com.lwhtarena.动态代理.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/19 23:10:37
 * @description 工厂类，生成增强过的目标类
 */
public class Factory {
    public static Base getInstance(CglibProxy proxy){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Base.class);
        enhancer.setCallback(proxy);//增强
        Base base = (Base) enhancer.create();
        return base;
    }
}
