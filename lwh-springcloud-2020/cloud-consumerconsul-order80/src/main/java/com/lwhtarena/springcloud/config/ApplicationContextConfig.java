package com.lwhtarena.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author liwh
 * @Title: ApplicationContextConfig
 * @Package com.lwhtarena.springcloud.config
 * @Description: 配置
 * @Version 1.0.0
 * @date 2020/5/5 21:07
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
