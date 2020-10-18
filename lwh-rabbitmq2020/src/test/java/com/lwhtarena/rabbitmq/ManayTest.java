package com.lwhtarena.rabbitmq;

import com.lwhtarena.rabbitmq.many.NeoSender1;
import com.lwhtarena.rabbitmq.many.NeoSender2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liwh
 * @Title: ManayTest
 * @Package com.lwhtarena.rabbitmq
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/18 20:54
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ManayTest {

    @Autowired
    private NeoSender1 sender1;
    @Autowired
    private NeoSender2 sender2;

    @Test
    public void oneToMany(){
        for (int i=0;i<100;i++){
            sender1.send(i);
        }
    }

    @Test
    public void manyToMany(){
        for(int i=0;i<100;i++){
            sender1.send(i);
            sender2.send(i);
        }
    }
}
