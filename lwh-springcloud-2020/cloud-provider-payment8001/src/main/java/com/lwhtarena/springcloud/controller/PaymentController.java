package com.lwhtarena.springcloud.controller;

import com.lwhtarena.springcloud.entities.CommonResult;
import com.lwhtarena.springcloud.entities.Payment;
import com.lwhtarena.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liwh
 * @Title: PaymentController
 * @Package com.lwhtarena.springcloud.controller
 * @Description: 控制层
 * @Version 1.0.0
 * @date 2020/5/3 20:23
 */
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(Payment payment){
        int result =paymentService.create(payment);
        log.info("*****插入结果：[{}]",result);

        if(result >0){
            return new CommonResult(200,"插入数据库记录成功！",result);
        }else{
            return new CommonResult(444,"插入数据库记录失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymenById(@PathVariable("id") Long id){
        Payment result =paymentService.getPaymentById(id);
        log.info("获取结果：[{}]",result);
        if(result !=null){
            return new CommonResult<Payment>(200,"查询成功",result);
        }else {
            return new CommonResult<Payment>(444,"不存在该记录,查询id为："+id,null);
        }
    }
}
