package com.lwhtarena.sc.controller;

import com.google.common.collect.Lists;
import com.lwhtarena.sc.common.Rest;
import com.lwhtarena.sc.model.Order;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liwh
 * @Title: OrderController
 * @Package com.lwhtarena.sc.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/17 23:12
 */
@Api(tags = "订单模块")
@RestController
@RequestMapping("/user")
public class OrderController {

    @ApiOperation(value = "查询订单列表")
    @PostMapping(value = "/list")
    public Rest<List<Order>> list(){
        Rest<List<Order>> rest=new Rest<>();
        List<Order> list= Lists.newArrayList(new Order(),new Order(),new Order(),new Order(),new Order(),new Order());
        rest.setData(list);
        return rest;
    }

    @ApiOperation(value = "根据订单id查询订单详情")
    @GetMapping("/queryById")
    public Rest<Order> queryById(@RequestParam(value = "id") String id){
        Rest<Order> userRest=new Rest<>();
        userRest.setData(new Order());

        return userRest;
    }

}
