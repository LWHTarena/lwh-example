package com.lwhtarena.oauth2.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liwh
 * @Title: HelloController
 * @Package com.lwhtarena.oauth2.web
 * @Description:
 * @Version 1.0.0
 * @date 2020/12/6 11:13
 */
@Api(tags = "测试")
@RestController
@RequestMapping("/api")
public class HelloController {

    @ApiOperation(value = "你好")
    @GetMapping("/hello")
    public String hello(String name){
        return "Hello "+name;
    }
}