package com.lwhtarena.springcloud.alibaba.dao;

import com.lwhtarena.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author liwh
 * @Title: OrderDao
 * @Package com.lwhtarena.springcloud.alibaba.dao
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/14 09:26
 */
@Mapper
public interface OrderDao {
    //1 新建订单
    void create(Order order);

    //2 修改订单状态，从零改为1
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
