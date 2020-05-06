package com.lwhtarena.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author liwh
 * @Title: OrderConsulController
 * @Package com.lwhtarena.springcloud.controller
 * @Description: 业务
 * @Version 1.0.0
 * @date 2020/5/5 21:09
 */
@RestController
@Slf4j
public class OrderConsulController {

    public static final String INVOME_URL ="http://connsul-providerconsul-payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String paymentConnsul(){
        String result =restTemplate.getForObject(INVOME_URL+"/payment/consul",String.class);
        return result;
    }

}
