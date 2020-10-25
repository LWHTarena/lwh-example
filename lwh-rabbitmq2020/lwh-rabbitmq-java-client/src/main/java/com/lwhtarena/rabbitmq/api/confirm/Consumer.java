package com.lwhtarena.rabbitmq.api.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author liwh
 * @Title: Consumer
 * @Package com.lwhtarena.rabbitmq.api.confirm
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/18 21:05
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.1.15");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("glmall");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();
        String exchangename ="test_confirm_exchange";
        String routekey ="confirm.#";
        String queuename ="test_confirm_queue";

        /**
         * 声明交换机和队列，然后进行绑定设置，最后制定路由key
         * **/
        channel.exchangeDeclare(exchangename,"topic",true);
        channel.queueDeclare(queuename,true,false,false,null);
        channel.queueBind(queuename,exchangename,routekey);

        /**创建消费者**/
    }
}
