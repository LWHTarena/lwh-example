package com.lwhtarena.zero.web.back.conf;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * <p>
 * <h2>简述：负责管理基本配置信息</h2>
 * <ol></ol>
 * <h2>功能描述：负责管理基本配置信息</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */

@Configurable
@PropertySource(value={"classpath:back.properties"})
public class BackConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name="schedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactoryBean(){
        return new SchedulerFactoryBean();
    }

}
