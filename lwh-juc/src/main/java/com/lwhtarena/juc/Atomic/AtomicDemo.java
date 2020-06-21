package com.lwhtarena.juc.Atomic;

/**
 * @author liwh
 * @Title: AtomicDemo
 * @Package com.lwhtarena.juc.Atomic
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/21 17:10
 */
public class AtomicDemo implements Runnable{
    private int serialNumber = 0;
//    private volatile int serialNumber = 0;
//    private AtomicInteger serialNumber = new AtomicInteger();

    @Override
    public void run(){

        try{
            Thread.sleep(200);
        }catch(InterruptedException e){

        }

        System.out.println(Thread.currentThread().getName() + ":" + getSerialNumber());
    }

    public int getSerialNumber(){
        return serialNumber++;
//        return serialNumber.getAndIncrement();
    }
}