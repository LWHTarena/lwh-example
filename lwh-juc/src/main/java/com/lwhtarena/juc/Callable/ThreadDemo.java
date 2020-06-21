package com.lwhtarena.juc.Callable;

import java.util.concurrent.Callable;

/**
 * @author liwh
 * @Title: ThreadDemo
 * @Package com.lwhtarena.juc.Callable
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/21 17:09
 */
public class ThreadDemo implements Callable<Integer> {

    // 需要实现的方法
    @Override
    public Integer call() throws Exception{
        // 计算 0~100 的和
        int sum = 0;

        for(int i=0; i<=100; i++){
            sum += i;
        }

        return sum;
    }
}