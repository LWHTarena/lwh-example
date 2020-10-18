package com.lwhtarena.rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liwh
 * @Title: FanoutReceiverA
 * @Package com.lwhtarena.rabbitmq.fanout
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/18 15:37
 */

@Component
@RabbitListener(queues = {"fanout.A"})
public class FanoutReceiverA {

    @RabbitHandler
    public void process(String message){
        System.out.println("fanout Receiver A: "+message);
    }
}
