package com.lwhtarena.线程.thread.demo03;

/**
 * @author：liwenhao
 * @Date:2017/1/25
 * @description:<p>锁对象的改变问题</p>
 * @version:v1.0
 */
public class ChangeLock {

    private String lock ="lock";

    private void method(){
        synchronized (lock){
            try {
                System.out.println("当前线程 : "  + Thread.currentThread().getName() + "开始");
                lock = "change lock";
                Thread.sleep(2000);
                System.out.println("当前线程 : "  + Thread.currentThread().getName() + "结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final ChangeLock changeLock =new ChangeLock();

        Thread t1 =new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.method();
            }
        },"t1");

        Thread t2 =new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.method();
            }
        },"t2");

        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }

}
