package com.lwhtarena.juc.CompareAndSwap;

/**
 * @author liwh
 * @Title: CompareAndSwap
 * @Package com.lwhtarena.juc
 * @Description: 模拟CAS 算法
 * @Version 1.0.0
 * @date 2020/6/17 02:46
 */
public class CompareAndSwap{

    private int value;

    // 获取内存值
    public synchronized int get(){
        return value;
    }

    // 无论更新成功与否,都返回修改之前的内存值
    public synchronized int compareAndSwap(int expectedValue, int newValue){
        // 获取旧值
        int oldValue = value;

        if(oldValue == expectedValue){
            this.value = newValue;
        }

        // 返回修改之前的值
        return oldValue;
    }

    // 判断是否设置成功
    public synchronized boolean compareAndSet(int expectedValue, int newValue){
        return expectedValue == compareAndSwap(expectedValue, newValue);
    }
}