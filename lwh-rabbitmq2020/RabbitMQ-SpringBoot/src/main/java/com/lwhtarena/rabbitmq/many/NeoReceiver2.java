package com.lwhtarena.rabbitmq.many;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/27 13:02:47
 * @description
 */
@Component
public class NeoReceiver2 {

    @RabbitListener(queues = "neo")
    public void process(String neo) {
        System.out.println("Receiver 2: " + neo);
    }
}
