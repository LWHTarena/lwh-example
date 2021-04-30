package com.lwhtarena.rabbitmq.ack;

import com.lwhtarena.rabbitmq.config.AckRabbitMQConfig;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/27 18:24:06
 * @description 消费者配置监听
 */
@Configuration
public class MessageListenerConfig {
    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;
    @Autowired
    private MyAckReceiver myAckReceiver;
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new  SimpleMessageListenerContainer(cachingConnectionFactory);
        // 监听队列名
        container.setQueueNames(AckRabbitMQConfig.ackDirectQueue);
        // 当前消费者数量
        container.setConcurrentConsumers(1);
        // 最大消费者数量
        container.setMaxConcurrentConsumers(1);
        // 手动确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 设置监听器
        container.setMessageListener(myAckReceiver);

        return container;
    }
}
