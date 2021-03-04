package com.lwhtarena.juc.lockdemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 实现自旋锁
 *  自旋锁好处，循环比较获取知道成功位置，没有类似wait的阻塞
 *  通过cas操作完成自旋锁，A线程先进来调用mylock方法自己持有锁5秒钟，B随后进来发
 *  现当前有线程持有锁，不是null，所以只能通过自旋锁等待，知道A释放锁后B随后抢到
 */
public class SpinlockDemo {
    public static void main(String[] args) {
        SpinlockDemo spinlockDemo = new SpinlockDemo();
        new Thread(()->{
            spinlockDemo.mylock();
            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){
                e.printStackTrace();
            }
            spinlockDemo.myunlock();
        },"Thread 1").start();

        new Thread(()->{
            spinlockDemo.mylock();
            spinlockDemo.myunlock();
        },"Thread 2").start();
    }

    //原子引用线程
    AtomicReference<Thread> atomicReference =new AtomicReference<>();
    public void mylock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t come in");
        while (!atomicReference.compareAndSet(null,thread)){
            System.out.println(Thread.currentThread().getName()+" wait...");
        }
    }

    public void myunlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"\t invoked myunlock");
    }
}
