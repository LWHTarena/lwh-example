package com.lwhtarena.线程.thread.demo03;

/**
 * @author：liwenhao
 * @Date:2017/1/25
 * @description:<p>使用synchronized代码块加锁,比较灵活</p>
 * @version:v1.0
 */
public class ObjectLock {

    public static void main(String[] args) {
        final ObjectLock objectLock = new ObjectLock();

        Thread t1 =new Thread(new Runnable() {
            @Override
            public void run() {
                objectLock.method01();
            }
        });

        Thread t2 =new Thread(new Runnable() {
            @Override
            public void run() {
                objectLock.method02();
            }
        });

        Thread t3 =new Thread(new Runnable() {
            @Override
            public void run() {
                objectLock.method03();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }

    private void method01() {
        synchronized (this){//对象锁
            try {
                System.out.println("调用 method01...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void method02() {//类锁
        synchronized (ObjectLock.class){
            try {
                System.out.println("调用 method02...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Object lock=new Object();
    private void method03() {//任何对象锁
        synchronized (lock){
            try {
                System.out.println("调用 method03...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
