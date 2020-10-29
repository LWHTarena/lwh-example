package com.elastic.job.quickstart;

import java.time.LocalDateTime;

/**
 * @author liwh
 * @Title: SimpleDemo01
 * @Package com.lwhtarena.elastic.job.quickstart
 * @Description: 线程
 * @Version 1.0.0
 * @date 2020/10/28 16:50
 */
public class SimpleDemo01 {

    public static void main(String[] args) {
        /**任务执行间隔时间**/
        final long timeInterval =1000;
        Runnable runnable =new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.printf("time:%s, to do .... \n", LocalDateTime.now());
                    try {
                        Thread.sleep(timeInterval);
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
