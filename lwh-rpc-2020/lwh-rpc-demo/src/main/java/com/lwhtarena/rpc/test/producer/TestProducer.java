package com.lwhtarena.rpc.test.producer;

import com.lwhtarena.rpc.config.ServiceConfig;
import com.lwhtarena.rpc.core.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liwh
 * @Title: TestProducer
 * @Package com.lwhtarena.rpc.test.producer
 * @Description: 测试生产者
 * @Version 1.0.0
 * @date 2020/9/19 22:34
 */
public class TestProducer {


    public static void main(String[] args) throws Exception {
        String connectionString = "zookeeper://172.16.18.44:2181,172.16.16.132:2182,172.16.16.133:2181";
        HelloService service = new HelloServiceImpl();
        ServiceConfig<HelloService> config = new ServiceConfig<>(HelloService.class, service);
        List<ServiceConfig> serviceConfigList = new ArrayList<>();
        serviceConfigList.add(config);
        ApplicationContext ctx = new ApplicationContext(connectionString, serviceConfigList, null, 50071);
    }
}
