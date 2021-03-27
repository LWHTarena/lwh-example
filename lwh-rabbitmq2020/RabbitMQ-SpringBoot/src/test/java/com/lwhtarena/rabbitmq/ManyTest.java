package com.lwhtarena.rabbitmq;

import com.lwhtarena.rabbitmq.many.NeoSender;
import com.lwhtarena.rabbitmq.many.NeoSender2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/27 13:00:44
 * @description
 */
@SpringBootTest
public class ManyTest {
    @Autowired
    private NeoSender neoSender;

    @Autowired
    private NeoSender2 neoSender2;

    @Test
    public void oneToMany() throws Exception {
        for (int i=0;i<100;i++){
            neoSender.send(i);
        }
    }

    @Test
    public void manyToMany() throws Exception {
        for (int i=0;i<100;i++){
            neoSender.send(i);
            neoSender2.send(i);
        }
    }
}
