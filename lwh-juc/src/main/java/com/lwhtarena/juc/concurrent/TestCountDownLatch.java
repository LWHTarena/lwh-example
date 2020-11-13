package com.lwhtarena.juc.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author liwh
 * @Title: TestCountDownLatch
 * @Package com.lwhtarena.juc.concurrent
 * @Description:
 *  CountDownLatch:闭锁，在完成某些运算是，只有其他所有线程的运算全部完成，当前运算才继续执行
 * @Version 1.0.0
 * @date 2020/11/13 12:14
 */
public class TestCountDownLatch {
    public static void main(String[] args) {
        final CountDownLatch latch =new CountDownLatch(50);
        LatchDemo demo = new LatchDemo(latch);
        long start = System.currentTimeMillis();
        for (int i=0;i<50;i++){
            new Thread(demo).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费时间时为："+(end-start));
    }
}
