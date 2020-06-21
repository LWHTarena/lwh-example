package com.lwhtarena.juc.Lock;

/**
 * @author liwh
 * @Title: TestABCAlternate
 * @Package com.lwhtarena.juc.lock
 * @Description:
 * // 练习: 程序按序交替
 * // 编写一个程序,开启3个线程,这三个线程的 ID 分别为 A, B, C, 每个线程将自己的 ID 在屏幕上打印10遍,
 * // 要求输出的结果必须按顺序显示:
 * // 如: ABCABCABC... 依次递归
 * @Version 1.0.0
 * @date 2020/6/21 17:00
 */
public class TestABCAlternate {
    public static void main(String[] args){
        AlternateDemo ad = new AlternateDemo();

        new Thread(new Runnable(){
            @Override
            public void run(){
                for(int i=1; i<20; i++){
                    ad.loopA(i);
                }
            }
        },"A").start();

        new Thread(new Runnable(){
            @Override
            public void run(){
                for(int i=1; i<20; i++){
                    ad.loopB(i);
                }
            }
        },"B").start();

        new Thread(new Runnable(){
            @Override
            public void run(){
                for(int i=1; i<20; i++){
                    ad.loopC(i);
                    System.out.println("--------------------");
                }
            }
        },"C").start();
    }
}
