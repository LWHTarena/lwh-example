package com.study.cache.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author liwh
 * @Title: AppConfig
 * @Package om.study.cache.redis.config
 * @Description: 用这个类 代替了xml文件
 * @Version 1.0.0
 * @date 2020/8/3 13:23
 */

@Configuration
public class AppConfig {

    String IP ="192.168.0.115";

    int PORT =6379;

    String PASSSWAORD ="tarena";

    int TIMEOUT =2000;

    /**
     * 创建对象，spring托管 <bean ...
     * @return
     */
    @Bean
    public JedisPool jedisPool(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1024);
        config.setMaxIdle(10);
        config.setMaxWaitMillis(1000);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);

        JedisPool jedisPool = new JedisPool(config,IP, PORT,TIMEOUT,PASSSWAORD);
        return jedisPool;
    }

}
