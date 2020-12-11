package com.lwhtarena.seata.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author liwh
 * @Title: ProductDao
 * @Package com.lwhtarena.seata.dao
 * @Description:
 * @Version 1.0.0
 * @date 2020/12/9 09:52
 */
@Mapper
public interface ProductDao{

    /**
     * 获取库存
     *
     * @param productId 商品 ID
     * @return 库存
     */
    @Select("SELECT stock FROM product WHERE id = #{productId}")
    Integer getStock(@Param("productId") Long productId);


    /**
     * 扣减库存
     *
     * @param productId 商品 ID
     * @param amount    扣减数量
     * @return 影响记录行数
     */
    @Update("UPDATE product SET stock = stock - #{amount} WHERE id = #{productId}")
    Integer reduceStock(@Param("productId") Long productId, @Param("amount") Integer amount);
}

