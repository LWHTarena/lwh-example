package com.lwhtarena.rabbitmq;

import com.lwhtarena.rabbitmq.hello.HelloSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liwh
 * @Title: HelloTest
 * @Package com.lwhtarena.rabbitmq
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/18 20:52
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class HelloTest {

    @Autowired
    private HelloSender sender;

    @Test
    public void  hello(){
        sender.send();
    }

}
