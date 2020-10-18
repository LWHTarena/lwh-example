package com.lwhtarena.rabbitmq;

import com.lwhtarena.rabbitmq.fanout.FanoutSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liwh
 * @Title: FanoutTest
 * @Package com.lwhtarena.rabbitmq
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/18 20:51
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class FanoutTest {

    @Autowired
    public FanoutSender sender;

    @Test
    public void fanoutSend(){
        sender.send();
    }
}
