package com.study.cache.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author liwh
 * @Title: JedisTests
 * @Package com.study.cache.redis
 * @Description: 使用jedis
 * @Version 1.0.0
 * @date 2020/8/3 13:30
 */
public class JedisTests {
    @Test
    public void test0() {
        // java客户端示例。 jedis初学者友好，操作和控制台类似
        Jedis jedis = new Jedis("192.168.1.8", 16379);
        String result = jedis.get("hello"); // get key
        System.out.println(result);

        // pool
        JedisPool jedisPool = new JedisPool();

        // controller -- 用户关注
        // 更新某个用户的粉丝数量
        // u10001 ---> 1000
        jedis.incr("u10001"); // incr
    }
}
