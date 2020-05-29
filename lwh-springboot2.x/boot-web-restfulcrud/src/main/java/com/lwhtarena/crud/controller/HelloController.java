package com.lwhtarena.crud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

/**
 * @author liwh
 * @Title: HelloController
 * @Package com.lwhtarena.crud.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/24 16:51
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/success")
    public String success(Map<String,Object> map){
        map.put("hello","您好");
        map.put("users", Arrays.asList("张三丰","王毅","疫情防护"));
        //classpath:/template/success
        return "success";
    }
}
