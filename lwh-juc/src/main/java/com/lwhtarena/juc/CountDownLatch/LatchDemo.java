package com.lwhtarena.juc.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author liwh
 * @Title: LatchDemo
 * @Package com.lwhtarena.juc.CountDownLatch
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/21 17:11
 */
public class LatchDemo implements Runnable{
    private CountDownLatch latch;

    // 有参构造器
    public LatchDemo(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run(){

        synchronized(this){
            try{
                // 打印50000以内的偶数
                for(int i=0; i<50000; i++){
                    if(i % 2 == 0){
                        System.out.println(i);
                    }
                }
            }finally{
                // 线程数量递减
                latch.countDown();
            }
        }
    }
}
