package com.lwhtarena.seata.controller;

import com.lwhtarena.seata.OperationResponse;
import com.lwhtarena.seata.order.PlaceOrderRequestVO;
import com.lwhtarena.seata.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liwh
 * @Title: OrderController
 * @Package com.lwhtarena.seata.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/12/9 10:16
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    @ResponseBody
    public OperationResponse placeOrder(@RequestBody PlaceOrderRequestVO placeOrderRequestVO) {
        log.info("收到下单请求,用户:{}, 商品:{}", placeOrderRequestVO.getUserId(), placeOrderRequestVO.getProductId());
        return orderService.placeOrder(placeOrderRequestVO);
    }
}
