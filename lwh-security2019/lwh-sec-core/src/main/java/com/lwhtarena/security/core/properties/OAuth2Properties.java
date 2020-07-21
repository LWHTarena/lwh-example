package com.lwhtarena.security.core.properties;

/**
 * @author liwh
 * @Title: OAuth2Properties
 * @Package com.lwhtarena.security.properties
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 16:22
 */
public class OAuth2Properties {
    /**
     * 使用jwt时为token签名的秘钥
     */
    private String jwtSigningKey = "imooc";
    /**
     * 客户端配置
     */
    private OAuth2ClientProperties[] clients = {};

    public OAuth2ClientProperties[] getClients() {
        return clients;
    }

    public void setClients(OAuth2ClientProperties[] clients) {
        this.clients = clients;
    }

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

    public void setJwtSigningKey(String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }
}
