package com.lwhtarena.rabbitmq.ack;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/04/30 05:10:37
 * @description 消费者手工ack和nack
 */
public class Consumer {
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

        String queueName = "test_ack_queue";
        String exchangeName = "test_ack_exchange";
        String routingKey = "ack.#";

        // 申明exchange
        channel.exchangeDeclare(exchangeName, "topic");
        // 申明队列
        channel.queueDeclare(queueName, true, false, false, null);
        // 队列绑定到exchange
        channel.queueBind(queueName, exchangeName, routingKey, null);

        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery message) throws IOException {

                // consumer手动 ack 给broker
                int num = (int)message.getProperties().getHeaders().get("num");
                // 根据headers里的num做判断，num<3,发ack给broker，并将消息重新入队
                if (num < 3) {
                    System.out.println("---消费端nack---DeliveryTag:" + message.getEnvelope().getDeliveryTag() + ","
                            + new String(message.getBody(), "UTF-8"));
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } else {
                    // 根据headers里的num做判断，num>=3,发nack给broker，并将消息重新入队
                    System.out.println("---消费端nack---DeliveryTag:" + message.getEnvelope().getDeliveryTag() + ","
                            + new String(message.getBody(), "UTF-8"));
                    channel.basicNack(message.getEnvelope().getDeliveryTag(), false, true);

                }

            }
        };
        CancelCallback cancelCallback = new CancelCallback() {
            @Override
            public void handle(String consumerTag) throws IOException {
                System.out.println("---消费者--：cancelCallback");
            }
        };

        // 消费消息,autoAck一定要设为false，手工ack
        channel.basicConsume(queueName, false, deliverCallback, cancelCallback);
    }
}

//发现前3条消息成功消费，手工发ack给Broker
//最后3条消息，发nack给Broker，并不断重回队列尾端，broker再将其推给消费端，一直循环消费失败