package com.lwhtarena.rabbitmq;

import com.lwhtarena.rabbitmq.Fanout.FanoutSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/27 12:46:13
 * @description
 */
@SpringBootTest
public class FanoutTest {
    @Autowired
    private FanoutSender sender;

    @Test
    public void fanoutSender() {
        sender.send();
    }

}
