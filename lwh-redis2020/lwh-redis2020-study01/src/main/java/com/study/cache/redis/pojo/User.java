package com.study.cache.redis.pojo;

import java.io.Serializable;

/**
 * @author liwh
 * @Title: User
 * @Package om.study.cache.redis.pojo
 * @Description:
 * @Version 1.0.0
 * @date 2020/8/3 13:25
 */
public class User implements Serializable {
    private String uname; // 名称
    private String uid; // uid
    private int age; // 年龄
    private String img; // 头像

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "User{" +
                "uname='" + uname + '\'' +
                ", uid='" + uid + '\'' +
                ", age=" + age +
                ", img='" + img + '\'' +
                '}';
    }
}
