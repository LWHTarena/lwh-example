package com.study.cache.redis.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liwh
 * @Title: NeteaseCache
 * @Package com.study.cache.redis.annotations
 * @Description: cache注解 -- 方法前 判断缓存，后 返回值 设置缓存
 * @Version 1.0.0
 * @date 2020/8/4 03:07
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NeteaseCache {
    /**
     * key的规则，可以使用springEL表达式，可以使用方法执行的一些参数
     */
    String key();

    /**
     *  类似前缀
     * @return
     */
    String value();
}
