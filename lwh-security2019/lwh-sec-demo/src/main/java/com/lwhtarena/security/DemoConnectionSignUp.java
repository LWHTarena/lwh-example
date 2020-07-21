package com.lwhtarena.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

/**
 * @author liwh
 * @Title: DemoConnectionSignUp
 * @Package com.lwhtarena.security
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 22:27
 */
//@Component
public class DemoConnectionSignUp implements ConnectionSignUp {

    /* (non-Javadoc)
     * @see org.springframework.social.connect.ConnectionSignUp#execute(org.springframework.social.connect.Connection)
     */
    @Override
    public String execute(Connection<?> connection) {
        //根据社交用户信息默认创建用户并返回用户唯一标识
        return connection.getDisplayName();
    }

}
