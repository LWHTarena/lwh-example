package com.lwhtarena.juc.lockdemo;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    /**使用内置创建线程池**/
    private static void threadPoolInit(){
        /**一池5个线程**/
//    ExecutorService threadPool =Executors.newFixedThreadPool(5);
        /**一池1个线程**/
//    ExecutorService threadPool =Executors.newSingleThreadExecutor();
        /**一池N个线程**/
        ExecutorService threadPool =Executors.newCachedThreadPool();
        try {
            for(int i=1;i<=20;i++){
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 来办理业务");
                });
                TimeUnit.MICROSECONDS.sleep(300);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

    /**自定义线程池**/
    public static void main(String[] args) {
        ExecutorService ccustomizeThreadPool =new ThreadPoolExecutor(
                2,5,1L,
                TimeUnit.SECONDS,new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        /**
         * new ThreadPoolExecutor.AbortPolicy();默认抛出异常
         * new ThreadPooLExecutor.CalLerRunsPolicy();回退调用者
         * new ThreadPooLExecutor.Discardpolicy();处理不了的不处理
         */
        try {
            for (int i=1;i<=20;i++){
                ccustomizeThreadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 来办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ccustomizeThreadPool.shutdown();
        }
    }
}
