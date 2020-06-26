package com.lwhtarena.dubbo.controller;

import com.lwhtarena.dubbo.bean.UserAddress;
import com.lwhtarena.dubbo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author liwh
 * @Title: OrderController
 * @Package com.lwhtarena.dubbo.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/26 07:43
 */
@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @ResponseBody
    @RequestMapping("/initOrder")
    public List<UserAddress> initOrder(@RequestParam("uid")String userId) {
        return orderService.initOrder(userId);
    }
}
