package com.lwhtarena.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liwh
 * @Title: RabbitConfig
 * @Package com.lwhtarena.rabbitmq.config
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/18 15:22
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue helloQueue(){
        return new Queue("hello");
    }

    @Bean
    public Queue neoQueue(){
        return new Queue("neo");
    }

    @Bean
    public Queue objectQueue(){
        return new Queue("object");
    }
}
