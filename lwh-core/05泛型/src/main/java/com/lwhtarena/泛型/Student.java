package com.lwhtarena.泛型;

/**
 * @author lwh
 * @folder com.lwhtarena.泛型
 * @date 2016/10/3.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class Student<T> {
    private T javaScore;
    private T oracleScore;

    //泛型声明时不能使用 静态属性|静态方法上
    //private static T1 test;

    public T getJavaScore() {
        return javaScore;
    }

    public void setJavaScore(T javaScore) {
        this.javaScore = javaScore;
    }

    public T getOracleScore() {
        return oracleScore;
    }

    public void setOracleScore(T oracleScore) {
        this.oracleScore = oracleScore;
    }

    public static void main(String[] args) {
        Student stu01 =new Student();

        //消除警告 使用Object
        Student<Object> stu =new Student<Object>();
        //stu.setJavaScore("af");//以Object对待

        test(stu01);//stu01 相当于object 但是不完全等同于Object

        //擦除，不会类型检查
        test1(stu01);
        test1(stu);

    }

    public static void test(Student<Integer> a){}

    public static void test1(Student<?> a){}
}
