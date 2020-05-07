package com.lwhtarena.springcloud.controller;

import com.lwhtarena.springcloud.entities.CommonResult;
import com.lwhtarena.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lwhtarena.springcloud.service.PaymentService;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(Payment payment){
        int result =paymentService.create(payment);
        log.info("*****插入结果：[{}]",result);

        if(result >0){
            return new CommonResult(200,"插入数据库记录成功！serverPort:"+serverPort,result);
        }else{
            return new CommonResult(444,"插入数据库记录失败!serverPort:"+serverPort,null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymenById(@PathVariable("id") Long id){
        Payment result =paymentService.getPaymentById(id);
        log.info("获取结果：[{}]",result);
        if(result !=null){
            return new CommonResult<Payment>(200,"查询成功,serverPort:"+serverPort,result);
        }else {
            return new CommonResult<Payment>(444,"不存在该记录,查询id为："+id+",serverPort:"+serverPort,null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("******* service:[{}]",service);
        }
        List<ServiceInstance> instances =discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    /**
     * 服务提供方8001故意写暂停程序
     * @return
     */
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTime(){
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }
        return serverPort;
    }
}
