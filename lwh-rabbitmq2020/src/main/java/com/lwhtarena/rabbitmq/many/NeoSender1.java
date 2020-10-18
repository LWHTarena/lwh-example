package com.lwhtarena.rabbitmq.many;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liwh
 * @Title: NeoSender1
 * @Package com.lwhtarena.rabbitmq.many
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/18 15:46
 */

@Component
public class NeoSender1 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(int i){
        String context ="spring boot neo queue ******** "+ i;
        System.out.println("发送者1："+ context);
        rabbitTemplate.convertAndSend("neo",context);
    }
}
