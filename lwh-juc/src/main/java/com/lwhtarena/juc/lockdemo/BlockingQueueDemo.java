package com.lwhtarena.juc.lockdemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueDemo {
    /**
     * 默认开启，进行生产消费工作
     */
    private volatile boolean flag =true;
    private AtomicInteger atomicInteger =new AtomicInteger();
    private BlockingQueue<String> blockingQueue;

    public BlockingQueueDemo(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void produce(){
        String data;
        boolean returnValue;
        while (flag){
            data =atomicInteger.incrementAndGet()+"";
            try {
                returnValue =blockingQueue.offer(data,2, TimeUnit.SECONDS);//往队列中放数据
                if(returnValue){
                    System.out.println(Thread.currentThread().getName()+"\t 插入队列的数据为："+data+"成功");
                }else {
                    System.out.println(Thread.currentThread().getName()+"\t 插入队列的数据为："+data+"失败");
                }
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()+"\t 停止标识 flag为："+flag);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void consume(){
        String result;
        while (flag){
            try {
                result =blockingQueue.poll(2,TimeUnit.SECONDS);
                if(null == result||"".equalsIgnoreCase(result)){
                    flag =false;
                    System.out.println(Thread.currentThread().getName()+"\t 没有取到数据");
                    return;
                }
                System.out.println(Thread.currentThread().getName()+"\t 消费者取到数据："+result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        flag =false;
    }
}

class TestDemo{
    public static void main(String[] args) {
        //创建一个容量为10的容器
        BlockingQueueDemo blockingQueueDemo =new BlockingQueueDemo(new ArrayBlockingQueue<>(10));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 生产线程启动");
            blockingQueueDemo.produce();
        },"生产者线程").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 消费线程启动");
            blockingQueueDemo.consume();
        },"消费者线程").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println("停止工作");
        blockingQueueDemo.stop();
    }
}

