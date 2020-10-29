package com.elastic.job.quickstart;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author liwh
 * @Title: SimpleDemo03
 * @Package com.elastic.job.quickstart
 * @Description: 线程池
 * @Version 1.0.0
 * @date 2020/10/28 17:10
 */
public class SimpleDemo03 {
    public static void main(String[] args) {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(10);
        threadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.printf("time:%s, to do .... \n", LocalDateTime.now());
            }
        },1,3, TimeUnit.SECONDS); //间隔3秒
    }
}
