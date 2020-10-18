package com.lwhtarena.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author liwh
 * @Title: ConsumerDelay
 * @Package com.lwhtarena.rabbitmq.listener
 * @Description: 监听消息队列
 * @Version 1.0.0
 * @date 2020/10/18 14:38
 */
@Component
@RabbitListener
public class ConsumerDelay {

    @RabbitHandler
    public void consumer(String message){
        System.out.println("监听的日志："+new Date());
        System.out.println(message);
    }
}
