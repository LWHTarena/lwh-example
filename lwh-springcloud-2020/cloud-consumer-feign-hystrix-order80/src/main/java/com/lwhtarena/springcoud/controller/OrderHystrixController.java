package com.lwhtarena.springcoud.controller;

import com.lwhtarena.springcoud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liwh
 * @Title: OrderHystrixController
 * @Package com.lwhtarena.springcoud.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/8 13:00
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_global_fallbackMethod")
public class OrderHystrixController {

    @Autowired(required = true)
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result =paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeOutFailbackMethod",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @HystrixCommand //没有特别指明就用统一的处理方法
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        String result =paymentHystrixService.paymentInfo_Timeout(id);
        return result;
    }

    /**
     * 降级信息处理
     * @param id
     * @return
     */
    public String paymentTimeOutFailbackMethod(@PathVariable("id") Integer id){
        return "我是消费者80，对方支持系统繁忙 请10秒钟后再试或者自己运行出错请检查自己";
    }

    /**
     * 下面是全局fallback方法
     * @return
     */
    public String payment_global_fallbackMethod(){
        return "全局异常通用处理信息，请稍后再试,99999";
    }
}
