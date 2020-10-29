package com.elastic.job.quickstart;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author liwh
 * @Title: SimpleDemo02
 * @Package com.elastic.job.quickstart
 * @Description: Timer
 * @Version 1.0.0
 * @date 2020/10/28 17:01
 */
public class SimpleDemo02 {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.printf("time:%s, to do .... \n", LocalDateTime.now());
            }
        }, TimeUnit.SECONDS.toMillis(1),TimeUnit.SECONDS.toMillis(2));  //1秒后开始调度，每2秒执行一次
    }
}
