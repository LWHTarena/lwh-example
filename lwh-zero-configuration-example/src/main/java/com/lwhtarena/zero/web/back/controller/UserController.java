package com.lwhtarena.zero.web.back.controller;

import com.alibaba.fastjson.JSON;
import com.lwhtarena.zero.web.bean.User;
import com.lwhtarena.zero.web.cache.CacheKey;
import com.lwhtarena.zero.web.cache.CacheService;
import com.lwhtarena.zero.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：用户请求处理器</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CacheService cacheService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")// isAuthenticated 如果用户不是匿名用户就返回true
    public String showHomePage() {
        try {

            User user = userService.loadUserByUsername("admin");

//            测试缓存服务
//            缓存用户对象到redis,以用户ID区分
            cacheService.set(CacheKey.LOGIN_USER_KEY + user.getId(), JSON.toJSONString(user));
//            从缓存中取出
            String userStr = cacheService.get(CacheKey.LOGIN_USER_KEY + user.getId());
//            进行反序列化
            User u = JSON.parseObject(userStr, User.class);
            if(u != null){
                logger.info("user:{}", u);
            }
            logger.info("load user ");
        }catch (Exception e){
            logger.error(e.getLocalizedMessage(), e);
        }

        return "/index/index";
    }

}
