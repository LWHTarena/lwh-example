package com.lwhtarena.juc.lockdemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    public static void main(String[] args) {
        Rld rld = new Rld();
        Thread thread1 = new Thread(rld, "t1");
        Thread thread2 = new Thread(rld, "t2");
        thread1.start();
        thread2.start();
    }
}
class Rld implements Runnable{
    private Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }
    private void get(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t get方法");
            set();
        }finally {
            lock.unlock();
        }

    }
    private void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t set方法");
        }finally {
            lock.unlock();
        }
    }
}
/**************************打印结果**************************/
/*
t1	 get方法
t1	 set方法
t2	 get方法
t2	 set方法
 */