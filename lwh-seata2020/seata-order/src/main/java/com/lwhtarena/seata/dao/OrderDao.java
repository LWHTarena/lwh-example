package com.lwhtarena.seata.dao;

import com.lwhtarena.seata.model.Order;
import com.lwhtarena.seata.model.OrderStatus;
import org.apache.ibatis.annotations.*;

/**
 * @author liwh
 * @Title: OrderDao
 * @Package com.lwhtarena.seata.dao
 * @Description:
 * @Version 1.0.0
 * @date 2020/12/9 10:17
 */
@Mapper
public interface OrderDao {

    /**
     * 保存订单
     *
     * @param order 订单
     * @return 影响行数
     */
    @Insert("INSERT INTO orders (user_id, product_id, pay_amount, status) VALUES (#{userId}, #{productId}, #{payAmount}, #{status})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    Integer saveOrder(Order order);

    /**
     * 更新订单状态
     *
     * @param id     订单 ID
     * @param status 状态
     * @return 影响行数
     */
    @Update("UPDATE orders SET status = #{status} WHERE id = #{id}")
    Integer updateOrder(@Param("id") Integer id, @Param("status") OrderStatus status);
}
