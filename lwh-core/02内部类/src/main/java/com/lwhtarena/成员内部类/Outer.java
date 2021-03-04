package com.lwhtarena.成员内部类;

/**
 * @author lwh
 * @folder com.lwhtarena.内部类
 * @date 2016/10/1.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class Outer {
    public static void main(String[] args) {
        Face f =new Face();
        Face.Nose n =f.new Nose();
        n.breath();
        System.out.println("===========================");
        Face.Ear e =new Face.Ear();
        e.listen();
    }
}


class Face{
    int type;
    String shape ="瓜子脸";
    static String color="红润";

    class Nose{
        void breath(){
            System.out.println(shape);
            System.out.println(Face.this.type);
            System.out.println("呼吸！");
        }
    }

    static class Ear{
        void listen(){
            System.out.println(color);
            System.out.println("我在听！");
        }
    }
}
