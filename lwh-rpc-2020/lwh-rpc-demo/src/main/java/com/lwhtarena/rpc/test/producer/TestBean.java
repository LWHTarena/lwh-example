package com.lwhtarena.rpc.test.producer;

/**
 * @author liwh
 * @Title: TestBean
 * @Package com.lwhtarena.rpc.test.producer
 * @Description: 测试Bean
 * @Version 1.0.0
 * @date 2020/9/19 22:33
 */
public class TestBean {

    private String name;
    private Integer age;

    public TestBean(String name, Integer age) {
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "TestBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

