package com.lwhtarena.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/27 11:46:01
 * @description springboot+rabbitmq
 */
@SpringBootApplication
public class RabbitMQApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMQApplication.class,args);
    }
}
