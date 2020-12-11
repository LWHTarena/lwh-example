package com.lwhtarena.seata.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author liwh
 * @Title: RestTemplateConfig
 * @Package com.lwhtarena.seata.config
 * @Description:
 * @Version 1.0.0
 * @date 2020/12/9 10:16
 */
@Component
public class RestTemplateConfig {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
