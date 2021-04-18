package com.lwhtarena.lettuce.service;

import org.springframework.data.redis.core.ZSetOperations;
import java.util.List;
import java.util.Set;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/04/18 11:07:42
 * @description
 */
public interface RedisService {
    void click();
    List<ZSetOperations.TypedTuple<Object>> search5();
    Set<Object> search7();
}
