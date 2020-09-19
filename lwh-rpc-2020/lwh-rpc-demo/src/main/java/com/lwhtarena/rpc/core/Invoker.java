package com.lwhtarena.rpc.core;

/**
 * @author liwh
 * @Title: Invoker
 * @Package com.lwhtarena.rpc.core
 * @Description: 调用器
 * @Version 1.0.0
 * @date 2020/9/19 22:25
 */
public interface Invoker<T> {


    /**
     * 调用过程
     */
    T invoke(Object[] args);

    /**
     * 设置响应结果
     *
     * @param result 响应结果
     */
    void setResult(String result);

}

