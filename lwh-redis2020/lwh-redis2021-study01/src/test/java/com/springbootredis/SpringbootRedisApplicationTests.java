package com.springbootredis;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootredis.pojo.User;
import com.springbootredis.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringbootRedisApplicationTests {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private RedisUtil redisUtil;


    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("myKey","myValue");
        System.out.println(redisTemplate.opsForValue().get("myKey"));
    }

    @Test
    public void test() throws JsonProcessingException {
        User user = new User();
        user.setName("liwenhao");
        user.setAge(29);
        /**真实开发，一般都使用json来传递对象**/
        String str = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user",str);
        String jsonstr = redisTemplate.opsForValue().get("user");
        System.out.println(jsonstr);
    }

    @Test
    public void test2() throws JsonProcessingException {
        User user = new User();
        user.setName("liwenhao");
        user.setAge(29);

//        redisTemplate.opsForValue().set("user2",user);
        String jsonstr = redisTemplate.opsForValue().get("user");
        System.out.println(jsonstr);
    }

    private static String REDIS_LOCK ="lwhlock";
    public void test3(){
        String val =UUID.randomUUID().toString()+Thread.currentThread().getName();
//        Boolean flag = redisTemplate.opsForValue().setIfAbsent(REDIS_LOCK, val);//setNX
//        redisTemplate.expire(REDIS_LOCK,10l, TimeUnit.SECONDS);//设置过期时间
        redisTemplate.opsForValue().setIfAbsent(REDIS_LOCK, val, 10l, TimeUnit.MINUTES);
    }

    public void demo(){
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.flushDb();
        connection.flushAll();
    }

    @Test
    public void demo1(){
        System.out.println(redisUtil.get("user"));
    }

}
