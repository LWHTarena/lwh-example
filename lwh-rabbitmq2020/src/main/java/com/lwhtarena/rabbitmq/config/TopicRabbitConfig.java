package com.lwhtarena.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liwh
 * @Title: TopicRabbitConfig
 * @Package com.lwhtarena.rabbitmq.config
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/18 15:25
 */

@Configuration
public class TopicRabbitConfig {

    public final static String message ="topic.message";
    public final static String infomation ="topic.infomation";

    @Bean
    public Queue queueMessage(){
        return new Queue(message);
    }

    @Bean
    public Queue queueInfomation(){
        return new Queue(infomation);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("exchange");
    }

    @Bean
    Binding bindingExchangeMessage(Queue queueMessage,TopicExchange exchange){
        return BindingBuilder.bind(queueMessage).to(exchange).with(message);
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueInfomation,TopicExchange exchange){
        return BindingBuilder.bind(queueInfomation).to(exchange).with("topix.#");
    }

}
