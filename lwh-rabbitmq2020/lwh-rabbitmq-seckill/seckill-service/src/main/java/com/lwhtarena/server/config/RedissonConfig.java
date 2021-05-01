package com.lwhtarena.server.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * redisson通用化配置
 * @Author:debug (SteadyJack)
 * @Date: 2019/7/2 10:57
 **/
@Configuration
public class RedissonConfig {

    @Autowired
    private Environment env;

    @Bean
    public RedissonClient redissonClient(){
        Config config=new Config();
        config.useSingleServer().setAddress("redis://192.168.56.15:6379").setPassword("tarena");
        RedissonClient client=Redisson.create(config);
        return client;
    }


}