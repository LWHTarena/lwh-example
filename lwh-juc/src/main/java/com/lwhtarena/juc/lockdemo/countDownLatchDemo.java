package com.lwhtarena.juc.lockdemo;

import java.util.concurrent.CountDownLatch;

//可以看出班长走之后还有四个学生被锁在了教室
//======解决方法===========
public class countDownLatchDemo {
    public static void main(String[] args) {
        try {
            CountDownLatch countDownLatch =new CountDownLatch(6);
            for (int i=0;i<6;i++){
                new Thread(()->{
                    countDownLatch.countDown();
                    System.out.println(Thread.currentThread().getName()+"离开了教室...");
                },i+"号学生").start();
            }
            countDownLatch.await();//这里相当于挡住，在countDownLatch还没有变0之前不能执行以下方法
            System.out.println("====================班长锁门=========================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
/**
 *******************打印结果******************
 * 1号学生离开了教室...
 * 2号学生离开了教室...
 * 0号学生离开了教室...
 * 3号学生离开了教室...
 * 4号学生离开了教室...
 * 5号学生离开了教室...
 * ====================班长锁门=========================
 */
