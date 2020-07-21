package com.lwhtarena.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author liwh
 * @Title: WeixinProperties
 * @Package com.lwhtarena.security.properties
 * @Description: 微信登录配置项
 * @Version 1.0.0
 * @date 2020/7/20 16:27
 */
public class WeixinProperties extends SocialProperties {

    /**
     * 第三方id，用来决定发起第三方登录的url，默认是 weixin。
     */
    private String providerId = "weixin";

    /**
     * @return the providerId
     */
    public String getProviderId() {
        return providerId;
    }

    /**
     * @param providerId the providerId to set
     */
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }


}
