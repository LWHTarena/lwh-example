package com.lwhtarena.rabbitmq.object;

import com.lwhtarena.rabbitmq.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liwh
 * @Title: ObjectReceiver
 * @Package com.lwhtarena.rabbitmq.object
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/18 20:37
 */

@RabbitListener(queues = "object")
@Component
public class ObjectReceiver {
    @RabbitHandler
    public void process(User user){
        System.out.println("接收信息 ："+user);
    }
}
