package com.lwhtarena.security.core.properties;

/**
 * @author liwh
 * @Title: OAuth2ClientProperties
 * @Package com.lwhtarena.security.properties
 * @Description: 认证服务器注册的第三方应用配置项
 * @Version 1.0.0
 * @date 2020/7/20 16:21
 */
public class OAuth2ClientProperties {

    /**
     * 第三方应用appId
     */
    private String clientId;
    /**
     * 第三方应用appSecret
     */
    private String clientSecret;
    /**
     * 针对此应用发出的token的有效时间
     */
    private int accessTokenValidateSeconds = 7200;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public int getAccessTokenValidateSeconds() {
        return accessTokenValidateSeconds;
    }

    public void setAccessTokenValidateSeconds(int accessTokenValidateSeconds) {
        this.accessTokenValidateSeconds = accessTokenValidateSeconds;
    }

}
