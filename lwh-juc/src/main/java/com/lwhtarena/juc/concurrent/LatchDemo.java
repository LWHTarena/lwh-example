package com.lwhtarena.juc.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author liwh
 * @Title: LatchDemo
 * @Package com.lwhtarena.juc.concurrent
 * @Description:
 * @Version 1.0.0
 * @date 2020/11/13 12:14
 */
public class LatchDemo implements Runnable{

    private CountDownLatch latch;

    public LatchDemo(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        synchronized (this){
            try {
                for (int i=0;i<50000;i++){
                    if(i%2 ==0){
                        System.out.println(i);
                    }
                }
            }finally {
                latch.countDown();
            }
        }
    }
}
