package com.lwhtarena.es.controller;

import com.lwhtarena.es.pojo.EsBean;
import com.lwhtarena.es.pojo.User;
import com.lwhtarena.es.service.IesService;
import com.lwhtarena.es.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/29 04:58:56
 * @description
 */
@RestController
@RequestMapping("/elastic")
public class ElasticController {

    @Autowired
    IesService iesService;

    @Autowired
    Userservice userservice;


    @GetMapping("/init1")
    public String init1(){
        iesService.create();
        // id name tags
        List<EsBean> list=new ArrayList<>();
        list.add(new EsBean(1,"张三锕爱","很帅,有很多人喜欢他"));
        list.add(new EsBean(2,"李四酷狗","很帅,但是讨厌他"));
        list.add(new EsBean(3,"王五王二爷","很丑,有是喜欢他"));
        list.add(new EsBean(4,"张三王二婆","很帅,有没人喜欢他"));
        iesService.saveAll(list);
        return "success";
    }

    @GetMapping("/init2")
    public String init2(){
        List<User> users = new ArrayList<>();
        users.add(new User(1l, 18,"柳岩", "123456"));
        users.add(new User(2l, 19,"范冰冰", "123456"));
        users.add(new User(3l, 20,"李冰冰", "123456"));
        users.add(new User(4l, 21,"锋哥", "123456"));
        users.add(new User(5l, 22,"小鹿", "123456"));
        users.add(new User(6l, 23,"韩红", "123456"));
        userservice.init(users);
        return "suceess";
    }

    @GetMapping("/get1")
    public SearchHits get1() throws IOException {
        return iesService.testForHigh();
    }

    @GetMapping("/get2")
    public  List<User> get2(){
        return userservice.testFindByAgeBetween(20, 30);
    }

    @GetMapping("/get3")
    @ResponseBody
    public  List<User> get3(){
        return userservice.testFindByAgeBetween(20, 30);
    }
}
