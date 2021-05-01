package com.lwhtarena.server.config;

import com.google.common.collect.Maps;
import com.lwhtarena.server.utils.Comm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * 通用化 Rabbitmq 配置
 */
@Configuration
@Slf4j
public class RabbitmqConfig {

    RabbitTemplate rabbitTemplate;

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 定制RabbitTemplate
     * 1、服务收到消息就会回调
     * 1、spring.rabbitmq.publisher-confirms: true
     * 2、设置确认回调
     * 2、消息正确抵达队列就会进行回调
     * 1、spring.rabbitmq.publisher-returns: true
     * spring.rabbitmq.template.mandatory: true
     * 2、设置确认回调ReturnCallback
     * <p>
     * 3、消费端确认(保证每个消息都被正确消费，此时才可以broker删除这个消息)
     * spring.rabbitmq.template.listener.simple.acknowledge-mode=manual #手动签收消息
     * 1、默认是自动确认的，只要消息接收到，客户端会自动确认，服务端就会移除这个消息
     * 问题：
     * 我们收到很多消息，自动回复给服务器ack，只有一个消息处理成功，宕机了。发生消息丢失。
     * 消费者手动确认模式。只要我们没有明确告诉MQ，货物被签收。没有ack，消息就一直是unchecked状态。
     * 即使Consumer宕机,消息不丢失，会重新变为Ready，下一次有新的Consumer连接进来就发送给他
     * <p>
     * 2、如何签收
     * channel.basicAck(deliveryTag,false);  //签收;业务成功完成就应该签收
     * channel.basicNack(deliveryTag,false,true); //拒签；业务失败，拒签
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // 开启Mandatory, 才能触发回调函数，无论消息推送结果如何都强制调用回调函数
        rabbitTemplate.setMandatory(true);
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setMessageConverter(messageConverter());

        /** 设置确认回调
         * 1、只要消息抵达Broker就ack=true
         * correlationData：当前消息的唯一关联数据(这个是消息的唯一id)
         * ack：消息是否成功收到
         * cause：失败的原因
         */
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause);
        });

        /**
         * 只要消息没有投递给指定的队列，就触发这个失败回调
         * message：投递失败的消息详细信息
         * replyCode：回复的状态码
         * replyText：回复的文本内容
         * exchange：当时这个消息发给哪个交换机
         * routingKey：当时这个消息用哪个路邮键
         */
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.warn("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message);
        });
        return rabbitTemplate;
    }

    //构建异步发送邮箱通知的消息模型
    @Bean
    public Queue successEmailQueue() {
        return new Queue(Comm.EMAIL_QUEUE, true);
    }

    @Bean
    public TopicExchange successEmailExchange() {
        return new TopicExchange(Comm.EMAIL_EXCHANGE, true, false);
    }

    @Bean
    public Binding successEmailBinding() {
        return BindingBuilder.bind(successEmailQueue()).to(successEmailExchange()).with(Comm.EMAIL_ROUTING_KEY);
    }


    //构建秒杀成功之后-订单超时未支付的死信队列消息模型
    @Bean
    public Queue successKillDeadQueue() {
        Map<String, Object> argsMap = Maps.newHashMap();
        argsMap.put("x-dead-letter-exchange", Comm.DEAD_EXCHANGE);
        argsMap.put("x-dead-letter-routing-key", Comm.DEAD_ROUTING_KEY);
        return new Queue(Comm.DEAD_QUEUE, true, false, false, argsMap);
    }

    //基本交换机
    @Bean
    public TopicExchange successKillDeadProdExchange() {
        return new TopicExchange(Comm.DEAD_PROD_EXCHANGE, true, false);
    }

    //创建基本交换机+基本路由 -> 死信队列 的绑定
    @Bean
    public Binding successKillDeadProdBinding() {
        return BindingBuilder.bind(successKillDeadQueue()).to(successKillDeadProdExchange()).with(Comm.DEAD_PROD_ROUTING_KEY);
    }

    //真正的队列
    @Bean
    public Queue successKillRealQueue() {
        return new Queue(Comm.DEAD_REAL_QUEUE, true);
    }

    //死信交换机
    @Bean
    public TopicExchange successKillDeadExchange() {
        return new TopicExchange(Comm.DEAD_EXCHANGE, true, false);
    }

    //死信交换机+死信路由->真正队列 的绑定
    @Bean
    public Binding successKillDeadBinding() {
        return BindingBuilder.bind(successKillRealQueue()).to(successKillDeadExchange()).with(Comm.DEAD_ROUTING_KEY);
    }
}






























































































