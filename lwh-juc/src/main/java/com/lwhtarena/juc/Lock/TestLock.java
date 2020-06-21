package com.lwhtarena.juc.Lock;

/**
 * @author liwh
 * @Title: TestLock
 * @Package com.lwhtarena.juc
 * @Description: 以卖票为例 ， 使用 lock 之前
 * @Version 1.0.0
 * @date 2020/6/17 03:05
 */
public class TestLock{
    public static void main(String[] args){
//        Ticket ticket = new Ticket();
//
//        new Thread(ticket,"1号窗口").start();
//        new Thread(ticket,"2号窗口").start();
//        new Thread(ticket,"3号窗口").start();
        Ticket2 ticket = new Ticket2();

        new Thread(ticket,"1号窗口").start();
        new Thread(ticket,"2号窗口").start();
        new Thread(ticket,"3号窗口").start();
    }
}