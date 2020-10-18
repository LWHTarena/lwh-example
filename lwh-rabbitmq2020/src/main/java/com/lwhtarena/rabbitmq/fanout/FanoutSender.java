package com.lwhtarena.rabbitmq.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author liwh
 * @Title: FanoutSender
 * @Package com.lwhtarena.rabbitmq.fanout
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/18 15:41
 */

@Component
public class FanoutSender {

    @Autowired
    private AmqpTemplate amqpTemplate;


    public void send(){
        String context ="hello "+ new Date();
        System.out.println("发送者："+ context);
        amqpTemplate.convertAndSend("fanoutExchange",context);
    }

}
