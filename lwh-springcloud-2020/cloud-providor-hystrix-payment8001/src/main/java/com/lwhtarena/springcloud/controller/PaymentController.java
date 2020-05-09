package com.lwhtarena.springcloud.controller;

import com.lwhtarena.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liwh
 * @Title: PaymentController
 * @Package com.lwhtarena.springcloud.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/7 22:17
 */
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result =paymentService.paymentInfo_OK(id);
        log.info("*********result:"+result+" serverPort:"+ serverPort);
        return result;
    }

    /**
     * 模拟业务超时
     * @param id
     * @return
     */
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        String result =paymentService.paymentInfo_TimeOut(id);
        log.info("*********result:"+result+" serverPort："+serverPort);
        return result;
    }

    /**
     * 服务熔断
     * @param id
     * @return
     */
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result =paymentService.paymentCircuitBreaker(id);
        log.info("&&&&&&result:"+result);
        return result;
    }

}
