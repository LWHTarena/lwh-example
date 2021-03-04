package com.lwhtarena.线程.thread.demo04;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author：liwenhao
 * @Date:2017/1/26
 * @description:<p>volatile关键字不具备synchronized关键字的原子性（同步）</p>
 * <P>AtomicInteger 可以实现原子性</P>
 * @version:v1.0
 */
public class VolatileNoAtomic extends Thread {
//    private static volatile int count; //不具备原子特性

    private static AtomicInteger count =new AtomicInteger(0);//jdk有一个原子性的类
    private static void addCount(){
        for (int i=0;i<1000;i++){
//            count++;
            count.incrementAndGet(); //相当于 ++
        }
        System.out.println(count);
    }

    public void run(){
        addCount();
    }

    public static void main(String[] args) {
        VolatileNoAtomic[] arr = new VolatileNoAtomic[10];
        for(int i=0;i<10;i++){
            arr[i] =new VolatileNoAtomic();
        }

        for (int i=0;i<10;i++){
            arr[i].start();
        }
    }
}
