package com.lwhtarena.seata.service;

import com.lwhtarena.seata.OperationResponse;
import com.lwhtarena.seata.order.PlaceOrderRequestVO;

/**
 * @author liwh
 * @Title: OrderService
 * @Package com.lwhtarena.seata.service
 * @Description:
 * @Version 1.0.0
 * @date 2020/12/9 10:18
 */
public interface OrderService {

    /**
     * 下单
     *
     * @param placeOrderRequestVO 请求参数
     * @return 下单结果
     */
    OperationResponse placeOrder(PlaceOrderRequestVO placeOrderRequestVO);
}
