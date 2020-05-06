package com.lwhtarena.springcloud.controller;

import com.lwhtarena.springcloud.entities.CommonResult;
import com.lwhtarena.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author liwh
 * @Title: OrderController
 * @Package com.lwhtarena.springcloud.controller
 * @Description: 订单服务
 * @Version 1.0.0
 * @date 2020/5/4 10:18
 */
@RestController
@Slf4j
public class OrderController {
//    public static final String PAYMENT_URL ="http://localhost:8001"; //单机版
    public static final String PAYMENT_URL ="http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        //写操作
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/{id}".replace("{id}",String.valueOf(id)),CommonResult.class);
    }

    @GetMapping("consumer/paymentt/getForEntity/{id}")
    public CommonResult<Payment> getpayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity =restTemplate.getForEntity(PAYMENT_URL+"/payment/get/{id}".replace("{id}",String.valueOf(id)),CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            log.info("状态码:[{}],头部信息:[{}]",entity.getStatusCode(),entity.getHeaders());
            return entity.getBody();
        }else {
            log.error("状态码:[{}],头部信息:[{}]",entity.getStatusCode(),entity.getHeaders());
            return new CommonResult<>(444,"操作失败");
        }
    }
}
