package com.lwhtarena.lettuce.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/04/18 19:05:24
 * @description
 */
@RestController
public class RedisLockController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/deduct-stock")
    public String DeductStock() {
        synchronized (this) {
            //查看数据库中是否有库存
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            //如果有库存则购买一个商品
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + "");
                System.out.println("扣减成功，剩余库存:" + realStock);
            } else {
                //如果没有库存，则扣减失败
                System.out.println("扣减失败，库存不足");
            }
        }
        return "ok！！！";
    }

    @GetMapping("/deduct-stock2")
    public String DeductStock2() {
        String LOCK_KEY = "deduct-stock-lock";
        String LOCK_VALUE = "deduct-stock-value";

        //尝试加锁
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(LOCK_KEY, LOCK_VALUE);

        //加锁失败
        if (!flag) {
            System.out.println("秒杀失败，请重试！！！");
        }

        //加锁成功
        if (flag) {
            //查看数据库中是否有库存
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            //如果有库存则购买一个商品
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + "");
                System.out.println("扣减成功，剩余库存:" + realStock);
            } else {
                //如果没有库存，则扣减失败
                System.out.println("扣减失败，库存不足");
            }
        }

        //释放锁
        Boolean delete = stringRedisTemplate.delete(LOCK_KEY);
        System.out.println("删除" + LOCK_KEY + ":" + delete); //打印日志
        return "ok！！！";
    }

    @GetMapping("/deduct-stock3")
    public String DeductStock3() {
        String LOCK_KEY = "deduct-stock-lock";
        RLock redissonLock = redissonClient.getLock(LOCK_KEY);
        //加锁成功
        try {
            redissonLock.lock();
            //查看数据库中是否有库存
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            //如果有库存则购买一个商品
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + "");
                System.out.println("扣减成功，剩余库存:" + realStock);
            } else {
                //如果没有库存，则扣减失败
                System.out.println("扣减失败，库存不足");
            }
        } finally {
            redissonLock.unlock();
            System.out.println("成功释放锁"); //打印日志
        }

        return "ok！！！";
    }
}
