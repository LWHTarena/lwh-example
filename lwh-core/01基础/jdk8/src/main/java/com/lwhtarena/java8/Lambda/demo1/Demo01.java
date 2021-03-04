package com.lwhtarena.java8.Lambda.demo1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @program basecore_java
 * @description:
 * @author: liwh
 * @create: 2018/12/08 23:06
 **/
public class Demo01 {

    List<Employee> employees = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99),
            new Employee(102, "李四", 59, 6666.66),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    // 1、需求
    @Test
    public void test1(){
        employees.stream()
                .filter((e) -> e.getAge() <=35)
                .forEach(System.out::println);

        System.out.println("---------------------");

        employees.stream()
                .map(Employee::getName)
                .limit(3)
                .sorted()
                .forEach(System.out::println);




    }

}
