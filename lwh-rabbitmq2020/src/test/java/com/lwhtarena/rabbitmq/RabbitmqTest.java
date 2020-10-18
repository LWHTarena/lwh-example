package com.lwhtarena.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author liwh
 * @Title: RabbitmqTest
 * @Package com.lwhtarena.rabbitmq
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/18 14:42
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitmqTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void send(){
        System.out.println("发送消息："+new Date());
        rabbitTemplate.convertAndSend("queue.message.delay", (Object) "您好，rabbitmq", new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                /**设置过期时间10s**/
                message.getMessageProperties().setExpiration("100000");
                return message;
            }
        });

        try {
            Thread.sleep(10000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
