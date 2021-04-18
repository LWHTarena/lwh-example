package com.lwhtarena.lettuce.service;

import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/04/18 11:07:53
 * @Description
 */
@Service
public class RedisServiceImpl implements RedisService{

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**ZINCRBY hotNews:20201221 1 完善低龄未成年人犯罪规定**/
    @Override
    public void click() {
        String title = (String) redisTemplate.opsForSet().randomMember("title");
        System.out.println(title);
//        String key ="hotNews:20210417";
        String prefix ="hotNews:";
        String key =prefix+ DateUtil.today().replace("-","");
        double x = 1.0;
        redisTemplate.opsForZSet().incrementScore(key,title,x);
    }

    /***
     * 描述
     * @param
     * @return
     * @author liwh
     * @date 2021/4/18 18:50
     * @version 1.0
     **/
    @Override
    public List<ZSetOperations.TypedTuple<Object>> search5() {
        String prefix ="hotNews:";
        String key =prefix+DateUtil.today().replace("-","");
        Set<ZSetOperations.TypedTuple<Object>> typedTuples = redisTemplate.opsForZSet().reverseRangeWithScores(key, 0, 4);
        List<ZSetOperations.TypedTuple<Object>> list=new ArrayList();
        for(ZSetOperations.TypedTuple<Object> s:typedTuples){
            list.add(s);
        }
        return list;
    }

    /***
     * 描述 3. 七日搜索榜单计算：
     *       ZUNIONSTORE hotNews:20201215-20201221  7
     * @param
     * @return
     * @author liwh
     * @date 2021/4/18 18:49
     * @version 1.0
     **/
    @Override
    public Set<Object> search7() {
        String prefix ="hotNews:";
        String str = DateUtil.format(DateUtil.offsetWeek(new Date(), -1), "yyyy-MM-dd");
        String key =prefix+str.replace("-","")+"-"+DateUtil.today().replace("-","");
        Set hash = new HashSet();
        String k1 ="hotNews:20210412";
        String k2 ="hotNews:20210413";
        String k3 ="hotNews:20210414";
        String k4 ="hotNews:20210415";
        String k5 ="hotNews:20210416";
        String k6 ="hotNews:20210417";
        String k7 ="hotNews:20210418";
        hash.add(k1);
        hash.add(k2);
        hash.add(k3);
        hash.add(k4);
        hash.add(k5);
        hash.add(k6);
        redisTemplate.opsForZSet().unionAndStore(k7, hash, key);

        /**range 正序**/
        Set<Object> set = redisTemplate.opsForZSet().reverseRange(key, 0, -1);
        return set;
    }
}
