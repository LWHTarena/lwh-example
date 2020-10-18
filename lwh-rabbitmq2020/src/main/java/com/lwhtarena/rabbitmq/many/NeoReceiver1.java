package com.lwhtarena.rabbitmq.many;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liwh
 * @Title: NeoReceiver1
 * @Package com.lwhtarena.rabbitmq.many
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/18 15:44
 */

@Component
@RabbitListener(queues = "neo")
public class NeoReceiver1 {

    @RabbitListener
    public void process(String neo){
        System.out.println("接收信息1 ："+neo);
    }
}
