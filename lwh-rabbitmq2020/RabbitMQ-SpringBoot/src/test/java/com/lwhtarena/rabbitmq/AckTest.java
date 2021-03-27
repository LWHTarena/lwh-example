package com.lwhtarena.rabbitmq;

import com.lwhtarena.rabbitmq.rb.hello.HelloSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/27 18:48:18
 * @description 确认消息
 */
@SpringBootTest
public class AckTest {

    @Autowired
    private HelloSender helloSender;

    @Test
    public void ackHello() throws Exception {
        helloSender.sendByAckTopic();
    }
}
