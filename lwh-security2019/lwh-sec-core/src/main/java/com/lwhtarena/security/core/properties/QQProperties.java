package com.lwhtarena.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author liwh
 * @Title: QQProperties
 * @Package com.lwhtarena.security.properties
 * @Description: QQ登录配置项
 * @Version 1.0.0
 * @date 2020/7/20 16:22
 */
public class QQProperties extends SocialProperties {
    /**
     * 第三方id，用来决定发起第三方登录的url，默认是 qq。
     */
    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
