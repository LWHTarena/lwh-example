package com.lwhtarena.rabbitmq;

import com.lwhtarena.rabbitmq.Topic.TopicSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/27 15:08:37
 * @description
 */
@SpringBootTest
public class TopicTest {

    @Autowired
    private TopicSender sender;

    @Test
    public void topic1() throws Exception {
        sender.send1();
    }

    @Test
    public void topic2() throws Exception {
        sender.send2();
    }
}
