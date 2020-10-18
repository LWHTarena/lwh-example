package com.lwhtarena.rabbitmq;

import com.lwhtarena.rabbitmq.model.User;
import com.lwhtarena.rabbitmq.object.ObjectSender;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liwh
 * @Title: ObjectTest
 * @Package com.lwhtarena.rabbitmq
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/18 20:57
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ObjectTest {

    @Autowired
    private ObjectSender sender;

    public void sendObject(){
        User user =new User();
        user.setName("neo");
        user.setPwd("123456");
        sender.send(user);
    }
}
