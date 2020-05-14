package com.lwhtarena.springcloud.alibaba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author liwh
 * @Title: Order
 * @Package com.lwhtarena.springcloud.alibaba.domain
 * @Description: 订单表
 * @Version 1.0.0
 * @date 2020/5/14 09:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    private Long id;

    private Long userId;

    private Long productId;

    private Integer count;

    private BigDecimal money;

    /**订单状态：0：创建中；1：已完结**/
    private Integer status;
}
