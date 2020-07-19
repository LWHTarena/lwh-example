package com.lwhtarena.thymeleaf.controller;

import com.lwhtarena.thymeleaf.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * @author liwh
 * @Title: TestController
 * @Package com.lwhtarena.thymeleaf.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/15 08:17
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping(value = "/hello")
    public String hello(Model model){
        model.addAttribute("message","hello");
        model.addAttribute("hello","欢迎liwh");
        return "demo01";
    }

    @RequestMapping("/demo")
    public String demo(Model model){
        model.addAttribute("hello","hello world");//qurest.setAttriubte(key,value);
        //设置数据 为list
        List<User> users = new ArrayList<>();
        users.add(new User(1001,"废青",20));
        users.add(new User(1002,"蟑螂",21));
        users.add(new User(1003,"特朗普",88));
        model.addAttribute("mylist",users);

        //设置数据 为map
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("id",1);
        dataMap.put("name","zhangsan");

        model.addAttribute("dataMap",dataMap);
        String[] names = new String[]{"zhangsan","lisi","wangwu"};
        model.addAttribute("names",names);

        //时间

        model.addAttribute("date",new Date());

        model.addAttribute("age",18);
        model.addAttribute("class1","a");
        model.addAttribute("class2","b");

        return "demo";
    }
}
