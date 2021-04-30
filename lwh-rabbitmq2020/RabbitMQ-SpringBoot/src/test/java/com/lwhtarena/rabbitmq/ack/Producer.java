package com.lwhtarena.rabbitmq.ack;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/04/30 05:10:07
 * @description 消费者手工ack和nack
 */
public class Producer {
    private static final String HOST = "192.168.56.15";
    private static final int PORT = 5672;
    private static final String USERNAME = "guest";
    private static final String PASSWORD = "guest";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setVirtualHost("lwhdemo");
        factory.setPort(PORT);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();
        String exchangeName = "test_ack_exchange";
        String routingKey = "ack.abc";
        // 申明exchange
        channel.exchangeDeclare(exchangeName, "topic");
        for (int i = 0; i < 6; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("num", i);
            AMQP.BasicProperties props =
                    new AMQP.BasicProperties.Builder().deliveryMode(2).contentEncoding("UTF-8").headers(map).build();

            String msg = "这是第" + i + "条ack消息";
            channel.basicPublish(exchangeName, routingKey, false, props, msg.getBytes("UTF-8"));
        }

        channel.close();
        connection.close();
    }
}
