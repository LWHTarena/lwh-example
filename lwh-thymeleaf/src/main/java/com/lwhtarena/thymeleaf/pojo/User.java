package com.lwhtarena.thymeleaf.pojo;

import java.io.Serializable;

/**
 * @author liwh
 * @Title: User
 * @Package com.lwhtarena.thymeleaf.pojo
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/15 08:54
 */
public class User implements Serializable {
    private Integer id;
    private String name;
    private Integer age;

    public User() {
    }

    public User(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
