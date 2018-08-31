package com.lwhtarena.sqlite.service;


import com.lwhtarena.sqlite.entity.User;

import java.util.List;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public interface UserService {

    void save(User user);

    List<User> list();
}
