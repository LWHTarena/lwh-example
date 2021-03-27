package com.lwhtarena.rabbitmq.model;

import java.io.Serializable;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/27 11:59:18
 * @descriptionra
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private String pass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", pass='" + pass + '\'' + '}';
    }
}
