package com.lwhtarena.springmvc.controller;

import com.lwhtarena.springmvc.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liwh
 * @Title: HelloController
 * @Package com.lwhtarena.springmvc.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/23 13:12
 */
@Controller
public class HelloController {

    @Autowired
    HelloService helloService;


    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        String hello = helloService.sayHello("tomcat..");
        return hello;
    }

    //  /WEB-INF/views/success.jsp
    @RequestMapping("/suc")
    public String success(){
        return "success";
    }


}
