package com.lwhtarena.rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liwh
 * @Title: FanoutReceiverB
 * @Package com.lwhtarena.rabbitmq.fanout
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/18 15:39
 */

@Component
@RabbitListener(queues = {"fanout.B"})
public class FanoutReceiverB {

    @RabbitHandler
    public void process(String message){
        System.out.println("fanout Receiver B: "+message);
    }
}
