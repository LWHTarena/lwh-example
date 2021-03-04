package com.lwhtarena.juc.lockdemo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    /**
     * 多线程同时操作，模拟高并发
     * 读取共享资源应该同时进行（共享）
     * 如果有一个线程想去写共享资源，就不应该有其他线程可以对该共享资源进行读写（独占）
     * @param args
     */
    public static void main(String[] args) {
        MyCache cache =new MyCache();
        for (int i=0;i<5;i++){
            final int temp =i;
            new Thread(()->{
                cache.put(temp+"",temp+"");
            }).start();
        }
        for (int i=0;i<5;i++){
            final int temp=i;
            new Thread(()->{
                cache.get(temp+"");
            }).start();
        }

    }
}
class MyCache{
    /**保证可见性**/
    private volatile Map<String,Object> map =new HashMap<>();
    private ReentrantReadWriteLock lock =new ReentrantReadWriteLock();
    ReentrantReadWriteLock.WriteLock writeLock =lock.writeLock();
    ReentrantReadWriteLock.ReadLock readLock =lock.readLock();

    /**写操作*/
    public void put(String key,Object value){
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" 正在写入...");
            //模拟网络延迟
            try {
                TimeUnit.MICROSECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+" 写入完成");
        }finally {
            writeLock.unlock();
        }
    }

    /**读操作**/
    public void get(String key){
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName()+" 正在读...");
            //模拟网路延迟
            try {
                TimeUnit.MICROSECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object res = map.get(key);
            System.out.println(Thread.currentThread().getName()+" 读取完成："+res);
        }finally {
            readLock.unlock();
        }
    }
}
/*********************打印结果************************/