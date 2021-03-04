package com.lwhtarena.juc.lockdemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier =new CyclicBarrier(6,()->{
            System.out.println("班长锁门离开教室...");
        });
        for (int i=0;i<6;i++){
            final int temp =i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"离开教室...");
                try {
//调用一次内部就会加1，与上面6呼应，等到6的时候就可以执行上面班车离开的方法
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },temp+"号学生").start();
        }
    }
}
/*********************打印结果************************/
/*
0号学生离开教室...
2号学生离开教室...
1号学生离开教室...
3号学生离开教室...
4号学生离开教室...
5号学生离开教室...
班长锁门离开教室...
 */