package com.lwhtarena.springcoud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author liwh
 * @Title: PaymentHystrixService
 * @Package com.lwhtarena.springcoud.service
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/8 12:53
 */
@Component
@FeignClient(value = "CLOUD-PROVIDOR-HYSTRIX-PAYMENT")
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id);
}
