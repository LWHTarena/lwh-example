package com.lwhtarena.springcloud.controller;

import com.lwhtarena.springcloud.entities.CommonResult;
import com.lwhtarena.springcloud.entities.Payment;
import com.lwhtarena.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liwh
 * @Title: OrderFeignController
 * @Package com.lwhtarena.springcloud.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/7 13:32
 */
@RestController
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    /**
     * Feign 自带负载均衡配置项
     * @param id
     * @return
     */
    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    /**
     * OpenFeign 默认等待1秒钟，超时报错
     *（默认Feign客户端只等待1秒钟，但是服务端处理需要超过1秒钟，导致Feign客户端不想等待了，直接返回报
     * 错。为了避免这样的情况，有时候我们需要设置Feign客户端的超时控制。）
     * @return
     */
    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        //openfeign-ribbon,客户端一般默认1秒钟 ；造请求超时
        return paymentFeignService.paymentFeignTimeout();
    }
}
