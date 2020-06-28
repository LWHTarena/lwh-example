package com.lwhtarena.springcloud.controller;

import com.lwhtarena.springcloud.entities.Dept;
import com.lwhtarena.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liwh
 * @Title: Deptcontroller
 * @Package com.lwhtarena.springcloud.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/28 15:07
 */
@RestController
public class Deptcontroller {

    @Autowired
    private DeptClientService service;

    @RequestMapping(value = "/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return this.service.get(id);
    }

    @RequestMapping(value = "/consumer/dept/list")
    public List<Dept> list() {
        return this.service.list();
    }

    @RequestMapping(value = "/consumer/dept/add")
    public Object add(Dept dept) {
        return this.service.add(dept);
    }
}
