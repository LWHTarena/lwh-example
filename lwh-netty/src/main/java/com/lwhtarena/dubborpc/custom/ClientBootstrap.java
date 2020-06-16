package com.lwhtarena.dubborpc.custom;

import com.lwhtarena.dubborpc.netty.NettyClient;
import com.lwhtarena.dubborpc.publicInterface.HelloService;

import java.util.concurrent.TimeUnit;

/**
 * @author liwh
 * @Title: ClientBootstrap
 * @Package com.lwhtarena.dubborpc.custom
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/16 04:35
 */
public class ClientBootstrap {
    /**这里定义协议头**/
    public static final String providerName = "HelloService#hello#";

    public static void main(String[] args) throws InterruptedException {
        /**创建一个消费者**/
        NettyClient customer =new NettyClient();

        /**创建代理对象**/
        HelloService  service = (HelloService) customer.getBean(HelloService.class, providerName);

        /**通过代理对象调用服务提供者提供的服务**/
        String res =service.hello("您好 dubbo~~");
        System.out.println("调用的结果 res="+res);

        for(;;){
            TimeUnit.SECONDS.sleep(2);
            String result =service.hello("您好 dubbo~~");
            System.out.println("调用的结果 result="+result);
        }
    }
}
