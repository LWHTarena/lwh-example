package com.lwhtarena.rabbitmq;

import com.lwhtarena.rabbitmq.model.User;
import com.lwhtarena.rabbitmq.object.ObjectSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/27 15:07:58
 * @description
 */
@SpringBootTest
public class ObjectTest {

    @Autowired
    private ObjectSender sender;

    @Test
    public void sendOject() throws Exception {
        User user=new User();
        user.setName("neo");
        user.setPass("123456");
        sender.send(user);
    }
}
