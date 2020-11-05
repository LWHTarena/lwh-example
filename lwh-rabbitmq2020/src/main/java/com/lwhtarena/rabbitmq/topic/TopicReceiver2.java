package com.lwhtarena.rabbitmq.topic;

import com.lwhtarena.rabbitmq.config.TopicRabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liwh
 * @Title: TopicReceiver2
 * @Package com.lwhtarena.rabbitmq.topic
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/18 20:43
 */
@Component
@RabbitListener(queues = TopicRabbitConfig.infomation)
public class TopicReceiver2 {

    @RabbitHandler
    public void process(String message){
        System.out.println("topic receiver2: "+ message);
    }
}