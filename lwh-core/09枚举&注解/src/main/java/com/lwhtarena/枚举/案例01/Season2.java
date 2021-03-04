package com.lwhtarena.枚举.案例01;

/**
 * 枚举类
 * @author lwh
 * @folder com.lwhtarena.枚举.案例01
 * @date 2016/10/6.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public enum Season2 implements TimeInfo {

    //1、必须在枚举类的第一行写出有哪些枚举值
    SPRING("春天","春风又绿江南岸"){
        @Override
        public String getTimeInfo() {
            return "2-5";
        }
    },
    SUMMER("夏天","映日荷花别样红"){
        @Override
        public String getTimeInfo() {
            return "5-8";
        }
    },
    FALL("秋天", "秋水共长天一色"){
        @Override
        public String getTimeInfo() {
            return "8-11";
        }
    },
    WINTER("冬天", "窗含西岭千秋雪"){
        @Override
        public String getTimeInfo() {
            return "11-2";
        }
    };

    private final String name;
    private final String desc;

    private Season2(String name,String desc){
        this.name =name;
        this.desc =desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
