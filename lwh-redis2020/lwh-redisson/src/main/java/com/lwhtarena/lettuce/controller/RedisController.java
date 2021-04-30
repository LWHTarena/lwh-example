package com.lwhtarena.lettuce.controller;

import com.lwhtarena.lettuce.service.RedisService;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.VoidType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

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

    /***
     * 描述 集合操作实现微博微信关注模型
     **/
    @GetMapping("/weibo")
    @ResponseBody
    public List weibo(){
        String z3_set="zhangsanSet";
        String l4_set="lisiSet";
        /***张三关注的人*/
        redisTemplate.opsForSet().add(z3_set, "李四","王五","周星驰");

        /***李四关注的人*/
        redisTemplate.opsForSet().add(l4_set, "张三","赵六","王五","周星驰");

        /**张三和李四共同关注的人**/
        Set<Object> set = redisTemplate.opsForSet().intersect(z3_set, l4_set); //intersect获取2个变量中的交集。ntersect(K key, Collection<K> otherKeys)获取多个变量之间的交集。

        /**张三关注的人也关注周星驰**/
        Set<Object> members = redisTemplate.opsForSet().members(z3_set);

        /**张三可能认识的人**/
        Set<Object> set2 = redisTemplate.opsForSet().difference(l4_set, z3_set);
        List list =new ArrayList();
        list.add(set);
        list.add(members);
        list.add(set2);
        return list;
    }

    @GetMapping("/wechat")
    public List wechat(){
        /***
         * 1.点赞： SADD  like:{消息ID}  {用户ID}
         * 2.取消点赞： SREM like:{消息ID}  {用户ID}
         * 3.检查用户是否点过赞： SISMEMBER  like:{消息ID}  {用户ID}
         * 4.获取点赞的用户列表： SMEMBERS like:{消息ID}
         * 5.获取点赞用户数： SCARD like:{消息ID}
         * 6.微博共同关注： sinter
         * 7.我关注的人也关注它（共同爱好）:
         *    SISMEMBER s1 one
         *    SISMEMBER s2 one
         **/
        String title ="like:1001";
        redisTemplate.opsForSet().add(title, "张译","张鲁一"); //张译、张鲁一点赞了文章1001

        redisTemplate.opsForSet().remove(title,"张鲁一"); //张鲁一 取消点赞

        // 匹配获取键值对，ScanOptions.NONE为获取全部键值对；ScanOptions.scanOptions().match("C").build()匹配获取键位map1的键值对,不能模糊匹配。
        Cursor<Object> scan = redisTemplate.opsForSet().scan(title, ScanOptions.NONE);//获取点赞用户数： SCARD like:{消息ID}
        while (scan.hasNext()){
            System.out.println("===> "+ scan.next());
        }

        List list =new ArrayList();
        return list;
    }
}
