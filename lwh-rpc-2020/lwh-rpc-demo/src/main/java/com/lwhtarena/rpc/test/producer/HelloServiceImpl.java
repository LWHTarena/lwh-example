package com.lwhtarena.rpc.test.producer;

/**
 * @author liwh
 * @Title: HelloServiceImpl
 * @Package com.lwhtarena.rpc.test.producer
 * @Description: 测试实现类
 * @Version 1.0.0
 * @date 2020/9/19 22:33
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String message) {
        return "牛逼，我收到了消息：" + message;
    }

    @Override
    public String sayHello(TestBean testBean) {
        return "牛逼,我收到了消息：" + testBean;
    }

    @Override
    public String sayHello() {
        return "牛逼啊，成功了";
    }
}

