package com.lwhtarena.zero.web.service;

import com.lwhtarena.zero.web.bean.User;
import com.lwhtarena.zero.web.dao.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserMapper userMapper;


    @Transactional
    public User loadUserByUsername(String username) {
        return userMapper.loadUserByUsername(username);
    }

    @Transactional
    public void saveUser(User user) {
        userMapper.saveUser(user);
//        测试异常后数据是否回滚
//        getError();
    }

    private void getError() {
        int i = 1 / 0;
        logger.info("i:{}" , i);
    }

}
