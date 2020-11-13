package com.lwhtarena.juc.concurrent;

/**
 * @author liwh
 * @Title: TestCopyOnWriteArrayList
 * @Package com.lwhtarena.juc.concurrent
 * @Description:
 *      CopyOnWriteArrayList/CopyOnWriteArraySet: 写入并复制
 * 注意：添加操作多时，效率低，因为每次添加时都会进行复制，开销非常大。并发迭代操作多时可以选择。
 *
 * @Version 1.0.0
 * @date 2020/11/13 11:53
 */
public class TestCopyOnWriteArrayList {
    public static void main(String[] args) {
        HelloWordThread thread = new HelloWordThread();
        for(int i=0;i<5;i++){
            new Thread(thread).start();
        }
    }
}
