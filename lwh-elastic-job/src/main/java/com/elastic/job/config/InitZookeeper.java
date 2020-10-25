package com.elastic.job.config;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liwh
 * @Title: InitZookeeper
 * @Package com.elastic.job.config
 * @Description:初始化Zookeeper配置
 * @Version 1.0.0
 * @date 2020/10/25 23:30
 */

@Configuration
public class InitZookeeper {

    @Value("${zookeeper.server}")
    private String serverList ;
    @Value("${zookeeper.namespace}")
    private String namespace ;

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter() {
        ZookeeperConfiguration config = new ZookeeperConfiguration(serverList, namespace) ;
        return new ZookeeperRegistryCenter(config);
    }
}
