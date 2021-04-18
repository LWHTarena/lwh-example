package com.lwhtarena.lettuce.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/04/18 17:30:12
 * @description
 */

@Configuration
public class RedisConfig {
    private static final StringRedisSerializer STRING_SERIALIZER = new StringRedisSerializer();

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory);
        //key序列化方式
        template.setKeySerializer(STRING_SERIALIZER);
        //value序列化
        template.setValueSerializer(STRING_SERIALIZER);
        //key hashmap序列化
        template.setHashKeySerializer(STRING_SERIALIZER);
        //value hashmap序列化
        template.setHashValueSerializer(STRING_SERIALIZER);
        return template;
    }

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        // 可以用"rediss://"来启用SSL连接
        config.useSingleServer().setAddress("redis://192.168.56.15:6379").setPassword("tarena");
        return Redisson.create(config);
    }
}
