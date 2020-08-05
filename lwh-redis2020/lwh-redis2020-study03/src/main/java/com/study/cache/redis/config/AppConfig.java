package com.study.cache.redis.config;

import com.study.cache.redis.controller.WebsocketChatHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
/**
 * @author liwh
 * @Title: AppConfig
 * @Package com.study.cache.redis.config
 * @Description: 用这个类 代替了xml文件
 * @Version 1.0.0
 * @date 2020/8/4 03:25
 */
@Configuration
@EnableWebSocket
public class AppConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    int port;

    @Value("${spring.redis.password}")
    String password;

    @Bean
    public RedisTemplate redisTemplate() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setPassword(password);
        RedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);

        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);//
        redisTemplate.setKeySerializer(StringRedisSerializer.UTF_8); // key -- string
        // redisTemplate.setValueSerializer();
        return redisTemplate;
    }

    // websocket拦截
    @Bean
    public WebSocketConfigurer webSocketConfigurer(WebsocketChatHandler websocketChatHandler) {
        return new WebSocketConfigurer() {
            @Override
            public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
                registry.addHandler(websocketChatHandler, "/ws/chat").setAllowedOrigins("*");
            }
        };
    }
}
