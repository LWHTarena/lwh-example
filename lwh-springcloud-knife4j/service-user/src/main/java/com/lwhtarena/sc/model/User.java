package com.lwhtarena.sc.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Random;

/**
 * @author liwh
 * @Title: User
 * @Package com.lwhtarena.sc.model
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/17 23:23
 */
@ApiModel(value = "用户")
public class User {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "工作")
    private String worker;

    @ApiModelProperty(value = "单位")
    private String company;

    public User(String name, String worker, String company) {
        this.name = name;
        this.worker = worker;
        this.company = company;
        this.age=new Random().nextInt(100);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
