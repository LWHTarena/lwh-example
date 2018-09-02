package com.lwhtarena.quartz.demo;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MyJob {

    @Scheduled(cron = "0/5** **?")
    public void run(){
        System.out.println("任务执行了---------"
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
