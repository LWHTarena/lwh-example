package com.lwhtarena.security.core.social.weixin.connect;

import org.springframework.social.oauth2.AccessGrant;

/**
 * @author liwh
 * @Title: WeixinAccessGrant
 * @Package com.lwhtarena.security.core.social.weixin.connect
 * @Description:
 * 微信的access_token信息。与标准OAuth2协议不同，微信在获取access_token时会同时返回openId,并没有单独的通过accessToke换取openId的服务
 *
 * 所以在这里继承了标准AccessGrant，添加了openId字段，作为对微信access_token信息的封装。
 * @Version 1.0.0
 * @date 2020/7/20 17:17
 */
public class WeixinAccessGrant extends AccessGrant {

    /**
     *
     */
    private static final long serialVersionUID = -7243374526633186782L;

    private String openId;

    public WeixinAccessGrant() {
        super("");
    }

    public WeixinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }

    /**
     * @return the openId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId the openId to set
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

}