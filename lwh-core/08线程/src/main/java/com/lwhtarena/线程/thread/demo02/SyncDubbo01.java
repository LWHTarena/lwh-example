package com.lwhtarena.线程.thread.demo02;

/**
 * @author：liwenhao
 * @Date:2017/1/24
 * @description:<p>synchronized的重入</p>
 * @version:v1.0
 */
public class SyncDubbo01 {

    public synchronized void method01(){
        System.out.println("method01...");
        method02();
    }

    public synchronized void method02() {
        System.out.println("method02...");
        method03();
    }

    public synchronized void method03() {
        System.out.println("method03...");
    }

    public static void main(String[] args) {
        final SyncDubbo01 sd =new SyncDubbo01();
        Thread t1 =new Thread(new Runnable() {
            @Override
            public void run() {
                sd.method01();
            }
        });

        t1.start();
    }
}
