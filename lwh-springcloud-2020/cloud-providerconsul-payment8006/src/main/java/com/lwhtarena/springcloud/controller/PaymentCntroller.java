package com.lwhtarena.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author liwh
 * @Title: PaymentCntroller
 * @Package com.lwhtarena.springcloud.controller
 * @Description: consul
 * @Version 1.0.0
 * @date 2020/5/5 20:49
 */
@RestController
@Slf4j
public class PaymentCntroller {

    @Value("${server.port}")
    private String serverport;

    @GetMapping(value = "/payment/consul")
    public String paymentConsul(){
        return "springcloud with consul:"+serverport+"\t" + UUID.randomUUID().toString();
    }
}
