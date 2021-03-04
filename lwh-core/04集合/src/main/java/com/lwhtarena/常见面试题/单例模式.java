package com.lwhtarena.常见面试题;

/**
 * @author lwh
 * @folder com.lwhtarena.常见面试题
 * @date 2016/10/10.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class 单例模式 {

    private static final  单例模式  m_instance = new 单例模式();
    /**
     * 私有的默认构造子
     */
    private 单例模式(){}

    /**
     * 静态工厂方法
     */
    public static 单例模式 getInstance(){
        return m_instance;
    }
}
