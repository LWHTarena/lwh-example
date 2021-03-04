package com.lwhtarena.线程.thread.demo06;

import javax.sound.midi.Soundbank;
import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author：liwenhao
 * @Date:2017/1/26
 * @description:<p>模拟队列</p>
 * <p>利用之前的知识，构造简单应用队列</p>
 * @version:v1.0
 */
public class MyQueue {
    //1、需要一个承载元素的集合
    private final LinkedList<Object> list =new LinkedList<Object>();

    //2、需要一个计数器
    private AtomicInteger count =new AtomicInteger(0);

    //2、需要指定上限和下限
    private final int minSize =0;

    private final int maxSize;

    //4、构造方法
    public MyQueue(int size){
        this.maxSize =size;
    }

    //5、初始化一个对象用于加锁
    private  final  Object lock =new Object();

    /***
     * put(anObject): 把anObject加到BlockingQueue里,如果BlockQueue没有空间,
     * 则调用此方法的线程被阻断，直到BlockingQueue里面有空间再继续.
     */
    public void put(Object obj){
        synchronized (lock){
            while (count.get()==this.maxSize){//容器已满
                try {
                    lock.wait(); //等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //加入元素
            list.add(obj);
            //计数器累加
            count.incrementAndGet();
            System.out.println("新加入的元素为："+obj);

            //通知另外一个线程（唤醒）
            lock.notify();
        }
    }


    /**
     * take: 取走BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等
     * 待状态直到BlockingQueue有新的数据被加入.
     */
    public Object take(){
        Object ret =null;
        synchronized (lock){
            while (count.get()==this.minSize){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //1 移除元素操作
            ret =list.removeFirst();
            //2 计数器递减
            count.decrementAndGet();
            //3 唤醒另外一个线程
            lock.notify();
        }
        return  ret;
    }

    public int getSize(){
        return  this.count.get();
    }

    public static void main(String[] args) {
        final MyQueue mq =new MyQueue(5);
        mq.put("q");
        mq.put("a");
        mq.put("z");
        mq.put("w");
        mq.put("s");

        System.out.println("当前容器的长度："+mq.getSize());

        Thread t1 =new Thread(new Runnable() {
            @Override
            public void run() {
                mq.put("x");
                mq.put("e");
            }
        },"t1");


        t1.start();

        Thread t2 =new Thread(new Runnable() {
            @Override
            public void run() {
                Object o1 =mq.take();
                System.out.println("移除的元素为："+o1);
                Object o2 =mq.take();
                System.out.println("移除的元素为"+o2);
            }
        },"t2");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();
    }


}
