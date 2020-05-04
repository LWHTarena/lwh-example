package com.lwhtarena.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author liwh
 * @Title: ApplicationContextConfig
 * @Package com.lwhtarena.springcloud.config
 * @Description: 配置
 * @Version 1.0.0
 * @date 2020/5/4 10:31
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    public RestTemplate getResTemplate(){
        return new RestTemplate();
    }
}
