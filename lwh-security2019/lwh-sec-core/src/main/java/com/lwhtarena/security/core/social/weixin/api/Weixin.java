package com.lwhtarena.security.core.social.weixin.api;

/**
 * @author liwh
 * @Title: Weixin
 * @Package com.lwhtarena.security.core.social.weixin.api
 * @Description: 微信API调用接口
 * @Version 1.0.0
 * @date 2020/7/20 17:13
 */
public interface Weixin {
    WeixinUserInfo getUserInfo(String openId);
}
