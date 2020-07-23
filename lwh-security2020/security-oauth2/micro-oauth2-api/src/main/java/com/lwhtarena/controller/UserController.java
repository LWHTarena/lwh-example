package com.lwhtarena.controller;

import com.lwhtarena.domain.UserDTO;
import com.lwhtarena.holder.LoginUserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liwh
 * @Title: UserController
 * @Package com.lwhtarena.controller
 * @Description: 获取登录用户信息接口
 * @Version 1.0.0
 * @date 2020/7/23 13:11
 */
@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private LoginUserHolder loginUserHolder;

    @GetMapping("/currentUser")
    public UserDTO currentUser() {
        return loginUserHolder.getCurrentUser();
    }

}
