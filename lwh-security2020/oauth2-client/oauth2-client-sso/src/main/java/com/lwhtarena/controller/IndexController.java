package com.lwhtarena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liwh
 * @Title: IndexController
 * @Package com.lwhtarena.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/23 10:44
 */
@RestController
public class IndexController {

    @Autowired
    private UserController userController;

    @GetMapping("/")
    public Object index(Authentication authentication) {
        return userController.getCurrentUser(authentication);
    }
}
