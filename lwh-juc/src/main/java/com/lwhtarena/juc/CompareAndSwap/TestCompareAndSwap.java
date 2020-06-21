package com.lwhtarena.juc.CompareAndSwap;

/**
 * @author liwh
 * @Title: TestCompareAndSwap
 * @Package com.lwhtarena.juc.CompareAndSwap
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/21 17:13
 */
public class TestCompareAndSwap {
    public static void main(String[] args){
        final CompareAndSwap cas = new CompareAndSwap();

        for(int i=0; i<10; i++){
            // 创建10个线程,模拟多线程环境
            new Thread(new Runnable(){
                @Override
                public void run(){
                    int expectedValue = cas.get();
                    boolean b = cas.compareAndSet(expectedValue, (int)(Math.random()*100));
                    System.out.println(b);
                }
            }).start();
        }
    }
}
