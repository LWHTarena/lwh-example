package com.lwhtarena.spring.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

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

    /**
     * 使用@Value赋值；
     * 	1、基本数值
     * 	2、可以写SpEL； #{}
     * 	3、可以写${}；取出配置文件【properties】中的值（在运行环境变量里面的值）
     */
    @Value("周星驰")
    private String name;

    @Value("#{20-2}")
    private Integer age;

    @Value("星爷")
    private String nickName;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
