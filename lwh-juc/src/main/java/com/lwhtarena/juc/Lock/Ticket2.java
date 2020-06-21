package com.lwhtarena.juc.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liwh
 * @Title: Ticket2
 * @Package com.lwhtarena.juc.lock
 * @Description: 使用 Lock
 * @Version 1.0.0
 * @date 2020/6/21 17:03
 */
public class Ticket2 implements Runnable {
    private int tick = 100;

    private Lock lock = new ReentrantLock();

    @Override
    public void run(){
        while(true){
            // 上锁
            lock.lock();

            try{
                if(tick > 0){
                    try{
                        Thread.sleep(200);
                    }catch(InterruptedException e){

                    }
                    System.out.println(Thread.currentThread().getName()+"完成售票,余票为: "+ --tick);
                }
            }finally{
                // 释放锁
                lock.unlock();
            }
        }
    }
}
