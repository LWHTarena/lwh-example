package com.lwhtarena.rabbitmq.hello;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author liwh
 * @Title: HelloSender
 * @Package com.lwhtarena.rabbitmq.hello
 * @Description: 发送
 * @Version 1.0.0
 * @date 2020/10/18 15:34
 */
@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){
        String context ="hello "+ new Date();
        System.out.println("发送者："+ context);
        rabbitTemplate.convertAndSend("hello",context);
    }
}
