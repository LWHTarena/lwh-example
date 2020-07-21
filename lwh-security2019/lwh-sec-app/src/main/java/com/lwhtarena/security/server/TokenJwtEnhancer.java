package com.lwhtarena.security.server;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liwh
 * @Title: TokenJwtEnhancer
 * @Package com.lwhtarena.security.server
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 20:52
 */
public class TokenJwtEnhancer implements TokenEnhancer {

    /* (non-Javadoc)
     * @see org.springframework.security.oauth2.provider.token.TokenEnhancer#enhance(org.springframework.security.oauth2.common.OAuth2AccessToken, org.springframework.security.oauth2.provider.OAuth2Authentication)
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> info = new HashMap<>();
        info.put("company", "imooc");

        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);

        return accessToken;
    }

}
