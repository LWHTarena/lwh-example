package com.lwhtarena.rpc.test.consumer;

import com.lwhtarena.rpc.config.ReferenceConfig;
import com.lwhtarena.rpc.core.ApplicationContext;
import com.lwhtarena.rpc.test.producer.HelloService;
import com.lwhtarena.rpc.test.producer.TestBean;

import java.util.Collections;

/**
 * @author liwh
 * @Title: TestConsumer
 * @Package com.lwhtarena.rpc.test.consumer
 * @Description: 测试消费者
 * @Version 1.0.0
 * @date 2020/9/19 22:32
 */
public class TestConsumer {


    public static void main(String[] args) throws Exception {
        String connectionString = "zookeeper://172.16.18.44:2181,172.16.16.132:2182,172.16.16.133:2181";
        ReferenceConfig<HelloService> config = new ReferenceConfig<>(HelloService.class);
        ApplicationContext ctx = new ApplicationContext(connectionString, null, Collections.singletonList(config),
                50070);
        HelloService helloService = ctx.getService(HelloService.class);
        System.out.println("sayHello(TestBean)结果为：" + helloService.sayHello(new TestBean("张三", 20)));
    }
}
