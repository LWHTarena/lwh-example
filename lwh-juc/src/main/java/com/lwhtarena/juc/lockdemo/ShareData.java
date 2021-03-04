package com.lwhtarena.juc.lockdemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ShareData {
    private int stock =0;
    private Lock lock =new ReentrantLock();
    Condition condition =lock.newCondition();

    /**生产**/
    private void product(){
        lock.lock();
        try {
            while (stock>0){//库存量大于0时停止生成
                condition.await();
            }
            stock++; //否则继续生成
            System.out.println(Thread.currentThread().getName()+"\t 生产者生产完毕，此时库存："+stock+"通知消费者消费");
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    /**消费*/
    private  void consume(){
        lock.lock();

        try {
            while (stock<1){//库存不足等待生产
                condition.await();
            }
            stock--; //否则继续消费
            System.out.println(Thread.currentThread().getName()+"\t 消费者消费完毕,此时库存："+stock+"通知生产者生产");
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        /**初始化库存为0，两个线程交替工作，一个生产一个消费**/
        ShareData shareData =new ShareData();
        new Thread(()->{
            for (int i=1;i<5;i++){
                shareData.product();
            }
        },"线程A").start();
        new Thread(()->{
            for (int i=1;i<5;i++){
                shareData.consume();
            }
        },"线程B").start();
    }
}
//******************打印结果********************
/**
 * 线程A	 生产者生产完毕，此时库存：1通知消费者消费
 * 线程B	 消费者消费完毕,此时库存：0通知生产者生产
 * 线程A	 生产者生产完毕，此时库存：1通知消费者消费
 * 线程B	 消费者消费完毕,此时库存：0通知生产者生产
 * 线程A	 生产者生产完毕，此时库存：1通知消费者消费
 * 线程B	 消费者消费完毕,此时库存：0通知生产者生产
 * 线程A	 生产者生产完毕，此时库存：1通知消费者消费
 * 线程B	 消费者消费完毕,此时库存：0通知生产者生产
 */
