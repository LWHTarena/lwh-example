package com.lwhtarena.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liwh
 * @Title: FeignConfig
 * @Package com.lwhtarena.springcloud.config
 * @Description: 设置日志Bean
 * @Version 1.0.0
 * @date 2020/5/7 20:27
 */
@Configuration
public class FeignConfig {

    /**
     * - NONE：默认的。不显示任何日志
     * - BASIC：仅记录请求方法、URL、响应状态码及执行时间
     * - HEADRES：除了BASIC中定义的信息之外，还有请求和响应的头信息
     * - FULL：除了HEADRES中定义的信息之外，还有请求和响应的正文及元数据
     *
     * 然后在yaml文件里需要开启日志的Feign客户端
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

}
