package com.lwhtarena.juc.lockdemo;

public class SyschronizedDemo {
    public static void main(String[] args) {
        new Thread(()->{
            sendMsg();
        },"t1").start();
        new Thread(()->{
            sendMsg();
        },"t2").start();
    }

    public synchronized static void sendMsg(){
        System.out.println(Thread.currentThread().getName()+"\t 发送短信");
        sendEmail();
    }
    public synchronized static void sendEmail(){
        System.out.println(Thread.currentThread().getName()+"\t 发送邮件");
    }
}
/**************************打印结果**************************/
/*
t1	 发送短信
t1	 发送邮件
t2	 发送短信
t2	 发送邮件
 */