package com.lwhtarena.rabbitmq.hello;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liwh
 * @Title: HelloReceiver
 * @Package com.lwhtarena.rabbitmq.hello
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/18 15:33
 */

@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {

    @RabbitListener
    public void process(String hello){
        System.out.println("接收信息："+hello);
    }
}
