package com.lwhtarena.嵌套;

/**
 * @author lwh
 * @folder com.lwhtarena.嵌套
 * @date 2016/10/3.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * 泛型嵌套 :由外带内拆分
 */
public class StudentApp {
    public static void main(String[] args) {
        Student<String> stu =new Student<String>();

        stu.setScore("优秀");
        System.out.println(stu.getScore());

        //泛型嵌套
        Bjsxt<Student<String>> bjsxt =new Bjsxt<Student<String>>();
        bjsxt.setStu(stu);
        stu =bjsxt.getStu();

        String score =stu.getScore();
        System.out.println(score);

        //========HashMap 使用了泛型嵌套============
        Map<String,String> map =new HashMap<>();
        map.put("aa","aa");
        map.put("b","liwenhao");

        Set<Map.Entry<String,String>> entrySet =map.entrySet();
        for(Map.Entry<String,String> entry:entrySet){
            String key =entry.getKey();
            String value =entry.getValue();
            System.out.println(key +" ---> "+value);

        }
    }
}
