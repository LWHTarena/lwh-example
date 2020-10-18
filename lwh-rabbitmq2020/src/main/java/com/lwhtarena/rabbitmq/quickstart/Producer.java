package com.lwhtarena.rabbitmq.quickstart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author liwh
 * @Title: Producer
 * @Package com.lwhtarena.rabbitmq.quickstart
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/17 22:10
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        /**
         * 1、创建连接工厂
         * 2、通过连接工厂创建连接
         * 3、通过connection创建channel
         * 4、通过channel发送数据
         * 5、记得要关闭相关的连接
         */
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.1.15");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("glmall");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        String msg ="您好，快速入门rabbitmq";
        channel.basicPublish("gl","hello-java-queue",null,msg.getBytes());

        channel.close();
        connection.close();
    }
}
