package com.lwhtarena.rabbitmq;

import com.lwhtarena.rabbitmq.rb.hello.HelloSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/27 12:04:48
 * @description demo hello
 */
@SpringBootTest
public class HelloTest {
    @Autowired
    private HelloSender helloSender;

    @Test
    public void hello() throws Exception {
        helloSender.send();
    }
}
