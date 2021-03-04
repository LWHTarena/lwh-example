package com.lwhtarena.juc.lockdemo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore =new Semaphore(3);//模拟三个车位
        /**模拟6辆车**/
        for (int i=0;i<=6;i++){
            new Thread(()->{
                try {
                    //抢到车位，这时候只能进来三辆车，超过三辆车进不来，等待有车辆离开
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t 抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"\t 停车2秒后，离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();//释放车位资源
                }
            },i+"号车辆").start();
        }
    }
}
/********************打印结果**********************/
/*
0号车辆	 抢到车位
1号车辆	 抢到车位
2号车辆	 抢到车位
2号车辆	 停车2秒后，离开车位
0号车辆	 停车2秒后，离开车位
1号车辆	 停车2秒后，离开车位
3号车辆	 抢到车位
4号车辆	 抢到车位
5号车辆	 抢到车位
3号车辆	 停车2秒后，离开车位
5号车辆	 停车2秒后，离开车位
4号车辆	 停车2秒后，离开车位
6号车辆	 抢到车位
6号车辆	 停车2秒后，离开车位
 */
