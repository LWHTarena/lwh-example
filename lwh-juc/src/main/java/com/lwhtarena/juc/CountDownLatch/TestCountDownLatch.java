package com.lwhtarena.juc.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author liwh
 * @Title: TestCountDownLatch
 * @Package com.lwhtarena.juc
 * @Description: 测试类: 计算多线程的执行时间
 * @Version 1.0.0
 * @date 2020/6/17 03:01
 */
public class TestCountDownLatch{
    public static void main(String[] args){
        final CountDownLatch latch = new CountDownLatch(10);
        LatchDemo ld = new LatchDemo(latch);

        long start = System.currentTimeMillis();

        // 创建10个线程
        for(int i=0; i<10; i++){
            new Thread(ld).start();
        }

        try{
            latch.await();
        }catch(InterruptedException e){

        }

        long end = System.currentTimeMillis();

        System.out.println("耗费时间为:"+(end - start));

    }
}
