package com.lwhtarena.线程.thread.demo04;

/**
 * @author：liwenhao
 * @Date:2017/1/25
 * @description:<p>volatile关键字的主要作用是使用变量在多个线程间可见。但是不具备原子性</p>
 * @version:v1.0
 */
public class RunThread extends Thread {

    private volatile boolean isRunning =true;

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void run(){
        System.out.println("进入run方法...");
        int i =0;
        while(isRunning ==true){
            //。。。

        }
        System.out.println("线程停止...");
    }

    public static void main(String[] args) {
        try {
            RunThread rt =new RunThread();
            rt.start();
            Thread.sleep(1000);

            rt.setRunning(false);
            System.out.println("isRunning的值已经被设置了false");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
