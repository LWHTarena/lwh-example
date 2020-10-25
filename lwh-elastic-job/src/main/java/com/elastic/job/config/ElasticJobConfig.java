package com.elastic.job.config;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.elastic.job.annotation.TaskJobSign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Map;

/**
 * @author liwh
 * @Title: ElasticJobConfig
 * @Package com.elastic.job.config
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/25 23:29
 */
@Configuration
public class ElasticJobConfig {

    @Resource
    private ApplicationContext applicationContext ;
    @Resource
    private ZookeeperRegistryCenter zookeeperRegistryCenter;

    @Value("${job-config.cron}")
    private String cron ;

    @Value("${job-config.shardCount}")
    private int shardCount ;
    @Value("${job-config.shardItem}")
    private String shardItem ;

    /**
     * 配置任务监听器
     */
    @Bean
    public ElasticJobListener elasticJobListener() {
        return new TaskJobListener();
    }

    /**
     * 初始化配置任务
     */
    @PostConstruct
    public void initTaskJob() {
        Map<String, SimpleJob> jobMap = this.applicationContext.getBeansOfType(SimpleJob.class);
        Iterator iterator = jobMap.entrySet().iterator();
        while (iterator.hasNext()) {
            // 自定义注解管理
            Map.Entry<String, SimpleJob> entry = (Map.Entry)iterator.next();
            SimpleJob simpleJob = entry.getValue();
            TaskJobSign taskJobSign = simpleJob.getClass().getAnnotation(TaskJobSign.class);
            if (taskJobSign != null){
                String cron = taskJobSign.cron() ;
                String jobName = taskJobSign.jobName() ;
                // 生成配置
                SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(
                        JobCoreConfiguration.newBuilder(jobName, cron, shardCount)
                                .shardingItemParameters(shardItem).jobParameter(jobName).build(),
                        simpleJob.getClass().getCanonicalName());
                LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(
                        simpleJobConfiguration).overwrite(true).build();
                TaskJobListener taskJobListener = new TaskJobListener();
                // 初始化任务
                SpringJobScheduler jobScheduler = new SpringJobScheduler(
                        simpleJob, zookeeperRegistryCenter,
                        liteJobConfiguration, taskJobListener);
                jobScheduler.init();
            }
        }
    }
}
