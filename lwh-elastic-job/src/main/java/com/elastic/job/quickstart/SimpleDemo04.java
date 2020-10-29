package com.elastic.job.quickstart;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author liwh
 * @Title: SimpleDemo04
 * @Package com.elastic.job.quickstart
 * @Description: 基于quartz
 * @Version 1.0.0
 * @date 2020/10/28 17:15
 */
public class SimpleDemo04 {
    public static void main(String[] args) throws SchedulerException {
        /**创建scheduler**/
        StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        /**创建jobdetail**/
        JobBuilder jobBuilder = JobBuilder.newJob(MyJob.class);
        jobBuilder.withIdentity("jobName","jobGroupName");
        JobDetail jobDetail = jobBuilder.build();

        /**创建触发的CronTrigger支持按日历调度**/
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trggername", "triggerGroupName")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                .build();

        /**创建触发的simpleTrigger 简单的间隔调度**/
//        SimpleTrigger trigger = TriggerBuilder.newTrigger()
//                .withIdentity("trggername", "triggerGroupName")
//                .startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();

        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }

    public static class MyJob implements Job {
        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            System.out.println("todo something ...");
        }
    }
}
