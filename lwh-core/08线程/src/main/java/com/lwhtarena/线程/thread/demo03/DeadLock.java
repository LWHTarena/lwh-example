package com.lwhtarena.线程.thread.demo03;

/**
 * @author：liwenhao
 * @Date:2017/1/25
 * @description:<p>死锁问题，在设计程序时就应该避免双方相互持有对方的锁的情况</p>
 * @version:v1.0
 */
public class DeadLock implements Runnable {

    private String tag;
    private static Object lock01 =new Object();
    private static Object lock02 =new Object();

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public void run() {
        if(tag.equals("a")){
            synchronized (lock01){
                try {
                    System.out.println("当前线程 : "  + Thread.currentThread().getName() + " 进入lock1执行");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock02){
                    System.out.println("当前线程 : "  + Thread.currentThread().getName() + " 进入lock2执行");
                }
            }
        }
        if(tag.equals("b")){
            synchronized (lock02){
                try {
                    System.out.println("当前线程 : "  + Thread.currentThread().getName() + " 进入lock2执行");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock01){
                    System.out.println("当前线程 : "  + Thread.currentThread().getName() + " 进入lock1执行");
                }
            }
        }

    }

    public static void main(String[] args) {
        DeadLock d1 =new DeadLock();
        d1.setTag("a");
        DeadLock d2 =new DeadLock();
        d2.setTag("b");

        Thread t1 =new Thread(d1,"t1");
        Thread t2 =new Thread(d2,"t2");

        t1.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();
    }
}
