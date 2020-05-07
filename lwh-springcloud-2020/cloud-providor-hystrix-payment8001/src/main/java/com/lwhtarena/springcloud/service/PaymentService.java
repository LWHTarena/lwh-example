package com.lwhtarena.springcloud.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author liwh
 * @Title: PaymentService
 * @Package com.lwhtarena.springcloud.service
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/7 22:16
 */
@Service
public class PaymentService {

    //成功
    public String paymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_OK,id:"+id+"\t"+" 哈哈哈";
    }

    //失败
    public String paymentInfo_TimeOut(Integer id){
        int timeNumber =3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_OK,id:"+id+"\t"+" 呜呜呜"+" 耗时（秒）"+timeNumber;
    }
}
