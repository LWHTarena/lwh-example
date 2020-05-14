package com.lwhtarena.springcloud.alibaba.controller;

import com.lwhtarena.springcloud.alibaba.domain.CommonResult;
import com.lwhtarena.springcloud.alibaba.domain.Order;
import com.lwhtarena.springcloud.alibaba.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liwh
 * @Title: OrderController
 * @Package com.lwhtarena.springcloud.alibaba.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/14 09:31
 */
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;


    @GetMapping("/order/create")
    public CommonResult create(Order order) {
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }
}
