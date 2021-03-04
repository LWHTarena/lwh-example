package com.lwhtarena.枚举.案例01;

/**
 * 枚举类: 一个类的对象是有限而且固定的
 * @author lwh
 * @folder com.lwhtarena.枚举.案例01
 * @date 2016/10/6.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class Season {
    //1. 因为枚举类的对象是有限个, 所以不能再类的外部创建类的对象
    private Season(String name,String desc){
        this.name = name;
        this.desc = desc;
    }
    //2. 因为对象是固定的, 所以属性是常量.
    private final String name;
    private final String desc;

    //3. 在类的内部创建对象. 但需要在类的外部能够访问到该对象, 而且还不能被修改.
    public static final Season SPRING = new Season("春天", "春风又绿江南岸");
    public static final Season SUMMER = new Season("夏天", "映日荷花别样红");
    public static final Season FALL = new Season("秋天", "秋水共长天一色");
    public static final Season WINTER = new Season("冬天", "窗含西岭千秋雪");

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
