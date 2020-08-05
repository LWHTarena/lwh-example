package com.study.cache.redis;

import com.study.cache.redis.pojo.User;
import com.study.cache.redis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author liwh
 * @Title: UserServiceTests
 * @Package com.study.cache.redis
 * @Description: 使用xml文件的形式
 * @Version 1.0.0
 * @date 2020/8/3 13:31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@WebAppConfiguration("src/main/resources")
public class UserServiceTests {

    @Autowired
    UserService userService;

    @Test
    public void test0(){
        User user = userService.findUserById("1");
        System.out.println(user.getUname());
        System.out.println(user);
    }
}
