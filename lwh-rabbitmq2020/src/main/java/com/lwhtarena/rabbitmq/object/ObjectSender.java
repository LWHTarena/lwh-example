package com.lwhtarena.rabbitmq.object;

import com.lwhtarena.rabbitmq.model.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liwh
 * @Title: ObjectSender
 * @Package com.lwhtarena.rabbitmq.object
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/18 20:38
 */

@Component
public class ObjectSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(User user){
        System.out.println("发送者："+ user);
        rabbitTemplate.convertAndSend("objec",user);
    }
}
