package com.lwhtarena.rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liwh
 * @Title: FanoutReceiverC
 * @Package com.lwhtarena.rabbitmq.fanout
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/18 15:40
 */

@Component
@RabbitListener(queues = {"fanout.C"})
public class FanoutReceiverC {

    @RabbitHandler
    public void process(String message){
        System.out.println("fanout Receiver C: "+message);
    }
}
