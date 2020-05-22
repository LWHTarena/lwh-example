package com.lwhtarena.spring.dao;

import org.springframework.stereotype.Repository;

/**
 * @author liwh
 * @Title: BookDao
 * @Package com.lwhtarena.spring.dao
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/22 16:50
 */
//名字默认是类名首字母小写
@Repository
public class BookDao {
    private String lable = "1";

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    @Override
    public String toString() {
        return "BookDao [lable=" + lable + "]";
    }
}
