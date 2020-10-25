package com.elastic.job.service;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.elastic.job.config.TaskJobListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liwh
 * @Title: TaskJobService
 * @Package com.elastic.job.service
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/25 23:38
 */
@Service
public class TaskJobService {

    @Resource
    private ZookeeperRegistryCenter zookeeperRegistryCenter;

    public void addTaskJob(final String jobName,final SimpleJob simpleJob,
                           final String cron,final int shardCount,final String shardItem) {
        // 配置过程
        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration.newBuilder(
                jobName, cron, shardCount)
                .shardingItemParameters(shardItem).build();
        JobTypeConfiguration jobTypeConfiguration = new SimpleJobConfiguration(jobCoreConfiguration,
                simpleJob.getClass().getCanonicalName());
        LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(
                jobTypeConfiguration).overwrite(true).build();
        TaskJobListener taskJobListener = new TaskJobListener();
        // 加载执行
        SpringJobScheduler jobScheduler = new SpringJobScheduler(
                simpleJob, zookeeperRegistryCenter,
                liteJobConfiguration, taskJobListener);
        jobScheduler.init();
    }

}
