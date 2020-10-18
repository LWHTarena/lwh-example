package com.lwhtarena.rabbitmq.quickstart;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author liwh
 * @Title: Consumer
 * @Package com.lwhtarena.rabbitmq.quickstart
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/17 22:09
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        /**
         * 1、创建连接工厂
         * 2、通过连接工厂创建连接
         * 3、通过connection创建channel
         */
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.1.15");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("glmall");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();


        /**
         * 4、声明一个队列
         * 参数：String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
         * 参数明细
         * 1、queue 队列名称
         * 2、durable 是否持久化，如果持久化，mq重启后队列还在
         * 3、exclusive 是否独占连接，队列只允许在该连接中访问，如果connection连接关闭队列则自动删除,如果将此参数设置true可用于临时队列的创建
         * 4、autoDelete 自动删除，队列不再使用时是否自动删除此队列，如果将此参数和exclusive参数设置为true就可以实现临时队列（队列不用了就自动删除）
         * 5、arguments 参数，可以设置一个队列的扩展参数，比如：可设置存活时间
         */
        String queuename="hello-java-queue";
        channel.queueDeclare(queuename,true,false,false,null);

        /**5、创建消费者**/
        DefaultConsumer consumer = new DefaultConsumer(channel){
            // 获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            /**
             * 当接收到消息后此方法将被调用
             * @param consumerTag  消费者标签，用来标识消费者的，在监听队列时设置channel.basicConsume
             * @param envelope 信封，通过envelope
             * @param properties 消息属性
             * @param body 消息内容
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //交换机
                String exchange = envelope.getExchange();
                //消息id，mq在channel中用来标识消息的id，可用于确认消息已接收
                long deliveryTag = envelope.getDeliveryTag();
                // body 即消息体
                String msg = new String(body,"utf-8");
                System.out.println(" [x] received : " + msg + "!");
            }
        };

        /**
         * 6、监听队列，第二个参数：是否自动进行消息确认。
         * 参数：String queue, boolean autoAck, Consumer callback
         * 参数明细：
         * 1、queue 队列名称
         * 2、autoAck 自动回复，当消费者接收到消息后要告诉mq消息已接收，如果将此参数设置为tru表示会自动回复mq，如果设置为false要通过编程实现回复
         * 3、callback，消费方法，当消费者接收到消息要执行的方法
         */
        channel.basicConsume(queuename, true, consumer);
    }
}
