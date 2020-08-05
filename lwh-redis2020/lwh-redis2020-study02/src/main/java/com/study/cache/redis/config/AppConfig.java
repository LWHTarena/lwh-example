package com.study.cache.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;

/**
 * @author liwh
 * @Title: AppConfig
 * @Package com.study.cache.redis.config
 * @Description:  用这个类 代替了xml文件
 * @Version 1.0.0
 * @date 2020/8/4 03:08
 */
@Configuration
@EnableCaching
@EnableAspectJAutoProxy // 开启AOP自动代理
public class AppConfig {

    @Value("${spring.redis.host}") // springel
    String host;

    @Value("${spring.redis.port}")
    int port;

    @Value("${spring.redis.password}")
    String password;

    // 创建对象，spring托管 <bean ...
    @Bean
    public JedisPool jedisPool() {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1024);
        config.setMaxIdle(10);
        config.setMaxWaitMillis(1000);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        int timeOut = 2000;

        JedisPool jedisPool = new JedisPool(config,host, port,timeOut,password);

        return jedisPool;
    }

    // TODO 待完善
    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setHostName(host);
//        redisStandaloneConfiguration.setPort(6379);

        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(Arrays.asList(
                "192.168.100.242:6381",
                "192.168.100.242:6382",
                "192.168.100.242:6383",
                "192.168.100.242:6384",
                "192.168.100.242:6385",
                "192.168.100.242:6386"
        ));
        // 自适应集群变化
        RedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration);
        return redisConnectionFactory;
    }

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory) ;//
        redisTemplate.setKeySerializer(StringRedisSerializer.UTF_8); // key -- string
        // redisTemplate.setValueSerializer();
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        CacheManager cacheManager = new RedisCacheManager(redisCacheWriter,redisCacheConfiguration);
        return cacheManager;
    }

}