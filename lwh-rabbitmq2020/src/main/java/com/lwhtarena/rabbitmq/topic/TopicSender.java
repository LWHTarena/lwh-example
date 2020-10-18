package com.lwhtarena.rabbitmq.topic;

import com.lwhtarena.rabbitmq.config.TopicRabbitConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liwh
 * @Title: TopicSender
 * @Package com.lwhtarena.rabbitmq.topic
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/18 20:44
 */

@Component
public class TopicSender {

    @Autowired
    private AmqpTemplate amqpTemplate;


    public void send1(){
        String context ="嗨，i am message 1";
        System.out.println("Sender :"+ context);
        amqpTemplate.convertAndSend("exchange", TopicRabbitConfig.message,context);
    }

    public void send2(){
        String context ="嗨，i am message 2";
        System.out.println("Sender :"+ context);
        amqpTemplate.convertAndSend("exchange", TopicRabbitConfig.infomation,context);
    }
}
