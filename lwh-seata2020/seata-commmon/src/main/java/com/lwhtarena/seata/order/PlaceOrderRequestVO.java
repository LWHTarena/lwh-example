package com.lwhtarena.seata.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liwh
 * @Title: PlaceOrderRequestVO
 * @Package com.lwhtarena.seata.order
 * @Description: 下单请求 VO
 * @Version 1.0.0
 * @date 2020/12/9 09:40
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderRequestVO {
    private Long userId;

    private Long productId;

    private Integer price;
}

