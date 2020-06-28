package com.lwhtarena.springcloud.controller;

import com.lwhtarena.springcloud.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author liwh
 * @Title: DeptController
 * @Package com.lwhtarena.springcloud.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/28 12:29
 */
@RestController
public class DeptController {

    private static final String REST_URL_PREFIX ="http://MICRO-PROVIDER-DEPT";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/dept/add")
    public boolean add(Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add",dept,Boolean.class);
    }

    @RequestMapping(value = "/consumer/dept/{id}")
    public Dept get(@PathVariable("id") long id){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id,Dept.class);
    }

    @RequestMapping(value = "/consumer/dept/list")
    public List<Dept> list(){
        return  restTemplate.getForObject(REST_URL_PREFIX+"/dept/list",List.class);
    }

    /**
     * 测试@EnableDiscoveryClient，消费端可以调用服务发现
     * @return
     */
    @RequestMapping(value = "/consumer/dept/discovery")
    public Object discovery(){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/discovery",Object.class);
    }

}
