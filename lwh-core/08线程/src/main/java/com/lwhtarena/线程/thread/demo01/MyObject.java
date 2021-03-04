package com.lwhtarena.线程.thread.demo01;

/**
 * @author：liwenhao
 * @Date:2017/1/24
 * @description:
 * <p>对象锁的同步和异步问题</p>
 * @version:v1.0
 */
public class MyObject {

    public synchronized void method01(){
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * synchronized
     */
    public void method02(){
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        final MyObject obj =new MyObject();

        Thread t1 =new Thread(new Runnable() {
            @Override
            public void run() {
                obj.method01();
            }
        },"t1线程");

        Thread t2 =new Thread(new Runnable() {
            @Override
            public void run() {
                obj.method02();
            }
        },"t2线程");

        t1.start();
        t2.start();
    }

    /**
     * 分析：
     * t1线程先持有object对象的Lock锁，t2线程可以以异步的方式调用对象中的非synchronized修饰的方法
     * t1线程先持有object对象的Lock锁，t2线程如果在这个时候调用对象中的同步（synchronized）方法则需等待，也就是同步
     */

}
