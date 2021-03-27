package com.lwhtarena.rabbitmq.rb.hello;

import com.lwhtarena.rabbitmq.config.AckRabbitMQConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/27 12:06:37
 * @description 发送消息
 */
@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }


    public String sendByAckTopic() {
        String msg = "hello 我是ack消息";
        rabbitTemplate.convertAndSend(AckRabbitMQConfig.ackDirectExchange, AckRabbitMQConfig.ackDirectRoute, msg);
        return "success";
    }
}
