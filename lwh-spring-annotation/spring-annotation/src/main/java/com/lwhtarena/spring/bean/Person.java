package com.lwhtarena.spring.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liwh
 * @Title: Persion
 * @Package com.lwhtarena.spring.bean
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/22 09:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

//    @Value("周星驰")
    private String name;

    private Integer age;

//    @Value("星爷")
    private String nickName;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
