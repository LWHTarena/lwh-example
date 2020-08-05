package com.study.cache.redis.controller;

import com.study.cache.redis.pojo.User;
import com.study.cache.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liwh
 * @Title: UserController
 * @Package com.study.cache.redis.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/8/4 03:12
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 查看所有信息
     */
    @RequestMapping("/findUserById")
    public User findUserById(String userId) {
        return userService.findUserById(userId);
    }

    /**
     * 查看姓名
     */
    @RequestMapping("/findUserNameById")
    public String findUserNameById(String userId) {
        return userService.findUserNameById(userId);
    }

}
