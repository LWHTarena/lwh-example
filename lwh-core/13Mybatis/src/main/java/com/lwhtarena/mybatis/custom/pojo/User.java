package com.lwhtarena.mybatis.custom.pojo;

/**
 * @author liwh
 * @Title: User
 * @Package com.lwhtarena.mybatis.custom.pojo
 * @Description:
 * <blockquote><pre>
 * #创建表，有id， name， age
 * create table user (
 *   ID INT(11) PRIMARY KEY AUTO_INCREMENT,
 *   NAME VARCHAR(18) DEFAULT NULL,
 *   AGE INT(11) DEFAULT NULL
 * )
 * </pre></blockquote>
 * @Version 1.0.0
 * @date 2020/6/20 04:32
 */
public class User {
    private int ID;
    private String name;
    private int age;

    public User() {
    }

    public User(int ID, String name, int age) {
        this.ID = ID;
        this.name = name;
        this.age = age;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
