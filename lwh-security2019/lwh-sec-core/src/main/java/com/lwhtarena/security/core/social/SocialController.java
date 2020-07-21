package com.lwhtarena.security.core.social;

import com.lwhtarena.security.core.social.support.SocialUserInfo;
import org.springframework.social.connect.Connection;

/**
 * @author liwh
 * @Title: SocialController
 * @Package com.lwhtarena.security.core.social
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 16:45
 */
public abstract class SocialController {

    /**
     * 根据Connection信息构建SocialUserInfo
     * @param connection
     * @return
     */
    protected SocialUserInfo buildSocialUserInfo(Connection<?> connection) {
        SocialUserInfo userInfo = new SocialUserInfo();
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setHeadimg(connection.getImageUrl());
        return userInfo;
    }

}
