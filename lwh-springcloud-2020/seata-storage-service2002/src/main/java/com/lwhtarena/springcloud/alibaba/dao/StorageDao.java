package com.lwhtarena.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author liwh
 * @Title: StorageDao
 * @Package com.lwhtarena.springcloud.alibaba.dao
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/14 12:44
 */
@Mapper
public interface StorageDao {

    /**扣减库存**/
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
