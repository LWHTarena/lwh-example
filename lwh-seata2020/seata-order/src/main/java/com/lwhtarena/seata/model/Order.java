package com.lwhtarena.seata.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liwh
 * @Title: Order
 * @Package com.lwhtarena.seata.model
 * @Description: 订单
 * @Version 1.0.0
 * @date 2020/12/9 10:17
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Integer id;

    private Long userId;

    private Long productId;

    private OrderStatus status;

    private Integer payAmount;
}

