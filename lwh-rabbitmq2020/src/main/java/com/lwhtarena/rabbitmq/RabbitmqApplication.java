package com.lwhtarena.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author liwh
 * @Title: RabbitmqApplication
 * @Package com.lwhtarena.rabbitmq
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/17 21:57
 */
@SpringBootApplication
public class RabbitmqApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApplication.class,args);
    }

    @Bean
    public Queue createQueue1(){
        return QueueBuilder.durable("queue.message.delay")
                .withArgument("x-dead-letter-exchange","dlx.exchange")
                .withArgument("x-dead-letter-routing-key","queue.message")
                .build();
    }

    /**创建**/
    @Bean
    public Queue createQueue2(){
        return new Queue("queue.message");
    }

    /**创建交换机**/
    public DirectExchange createDirectExchange(){
        return new DirectExchange("dlx.exchange");
    }

    public Binding binding(){
        return BindingBuilder.bind(createQueue2()).to(createDirectExchange()).with("queue.message");
    }
}
