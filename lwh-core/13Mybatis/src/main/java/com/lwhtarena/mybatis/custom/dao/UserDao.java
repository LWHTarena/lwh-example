package com.lwhtarena.mybatis.custom.dao;

import com.lwhtarena.mybatis.custom.pojo.User;

import java.util.List;

/**
 * @author liwh
 * @Title: UserDao
 * @Package com.lwhtarena.mybatis.custom.dao
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/20 16:31
 */
public interface UserDao {
    
    List<User> selectAll();

    int saveUser();

    User getUserByName(String name);
}
