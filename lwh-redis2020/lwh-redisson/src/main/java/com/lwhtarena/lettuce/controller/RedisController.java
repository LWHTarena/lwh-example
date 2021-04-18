package com.lwhtarena.lettuce.controller;

import com.lwhtarena.lettuce.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/04/18 11:06:02
 * @description
 */

@RestController
@RequestMapping("/redis")
public class RedisController {
    /**
     * redis 实现搜索热词统计:核心需求
     * 一个项目中，遇到了搜索热词统计的需求，我使用了Redis的五大数据类型之一 Sorted Set实现。目前有两项数据
     * 需要统计：“当日搜索热词 top10”和“当周搜索热词 top10”。
     *
     * 关于这两项数据的统计方法，目前想到了两种实现方法：
     *  1、两个Redis的Sorted Set实现，一个Sorted Set A统计当天，0点top10记录进MySQL，Sorted Set清零。一个
     *   Sorted Set B统计当周，每周日top10记录进 MySQL，Sorted Set B清零。
     *  2、只使用用一个Sorted Set记录当天搜索热词，0点top10记录进MySQL，Sorted Set清零。到周日时，会有7 10行记录。
     *   把这7 10行遍历，每次便利都记录进Sorted Set，全部遍历结束后，再从Sorted Set 中取出top10记录进MySQL的周热词统计表中。
     *
     * Sorted Set 是 Redis 的数据结构，方法1会占用两份内存，一份当天的，一份当周的。方法2会提高系统的复杂度，并且在统计周表时，
     * 可能会出现短时间内大量的计算（当然可以使用定时任务,把周表的统计放到凌晨进行）。
     *
     * 最后选择了方案1，分开维护清晰明了。
     *  至于内存占用问题，1MB = 1048576 字节，按两个字节存一个字算，理论上1MB 能存 1048576/2/8 = 65,536 个不重复的搜索关键词
     *  （当然使用Sorted Set肯定比纯字更多占用一些空间）。多投入一些内存，能存下的数量还是很大的，通常可以撑到每周结束清理内存。一般的
     *  CRUD项目不用怎么考虑内存占用。
     */

    @Autowired
    RedisService redisService;

    /**
     * 点击
     */
    @GetMapping("/click")
    public String click(){
        redisService.click();
        return "success";
    }

    /**
     *  展示当日排行前5：
     *  ZREVRANGE hotNews:20201221 0 9 WITHSCORES
     * @return
     */
    @GetMapping("/search5")
    public List<ZSetOperations.TypedTuple<Object>> search5(){
        List<ZSetOperations.TypedTuple<Object>> list = redisService.search5();
        return list;
    }

    /**
     * 七日搜索榜单计算
     * ZUNIONSTORE hotNews:20201215-20201221  7
     * @return
     */
    @GetMapping("/search7")
    public Set<Object> search7(){
        Set<Object> set =redisService.search7();
        return set;
    }
}
