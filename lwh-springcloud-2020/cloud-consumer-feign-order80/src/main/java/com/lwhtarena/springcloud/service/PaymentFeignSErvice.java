package com.lwhtarena.springcloud.service;

import com.lwhtarena.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author liwh
 * @Title: PaymentFeignSErvice
 * @Package com.lwhtarena.springcloud.service
 * @Description: 业务逻辑接口 + @@FeignClient配置调用Provider服务
 * @Version 1.0.0
 * @date 2020/5/7 13:26
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignSErvice {

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);
}
