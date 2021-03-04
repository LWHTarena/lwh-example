package com.lwhtarena.线程.thread.demo05;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：liwenhao
 * @Date:2017/1/26
 * @description:<p>原始代码监听，比较ListAdd02使用wait和notify来实现</p>
 * @version:v1.0
 */
public class ListAdd01 {

    private volatile static List list = new ArrayList();

    public void add(){
        list.add("bjsxt");
    }
    public int size(){
        return list.size();
    }


    public static void main(String[] args) {
        final ListAdd01 list01 =new ListAdd01();

        Thread t01 =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i=0;i<10;i++) {
                        list01.add();
                        System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"t01");

        Thread t02 =new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (list01.size()==5){
                        System.out.println("当前线程收到通知：" + Thread.currentThread().getName() + " list size = 5 线程停止..");
                        throw new RuntimeException();
                    }
                }
            }
        },"t02");

        t01.start();
        t02.start();
    }

}
