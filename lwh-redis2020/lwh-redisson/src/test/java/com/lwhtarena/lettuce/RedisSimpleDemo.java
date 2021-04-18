package com.lwhtarena.lettuce;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/04/18 18:06:56
 * @description
 */

@Slf4j
@SpringBootTest
public class RedisSimpleDemo {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test1() {
        redisTemplate.multi();
        redisTemplate.opsForValue().set("k1", "Hello Redis1....");
        int i = 100 / 0;
        redisTemplate.opsForValue().set("k2", "Hello Redis2....");
        redisTemplate.exec();
    }

    @Test
    public void test2() {
        stringRedisTemplate.multi();
        stringRedisTemplate.opsForValue().set("k1", "Hello Redis1....");
        int i = 100 / 0;
        stringRedisTemplate.opsForValue().set("k2", "Hello Redis2....");
        stringRedisTemplate.exec();
    }
}
