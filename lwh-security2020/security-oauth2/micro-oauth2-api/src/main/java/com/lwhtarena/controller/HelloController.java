package com.lwhtarena.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liwh
 * @Title: HelloController
 * @Package com.lwhtarena.controller
 * @Description: 测试接口
 * @Version 1.0.0
 * @date 2020/7/23 13:11
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World.";
    }

}
