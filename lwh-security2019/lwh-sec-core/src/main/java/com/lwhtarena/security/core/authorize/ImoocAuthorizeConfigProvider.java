package com.lwhtarena.security.core.authorize;

import com.lwhtarena.security.core.properties.SecurityConstants;
import com.lwhtarena.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author liwh
 * @Title: ImoocAuthorizeConfigProvider
 * @Package com.lwhtarena.security.core.authorize
 * @Description: 核心模块的授权配置提供器，安全模块涉及的url的授权配置在这里。
 * @Version 1.0.0
 * @date 2020/7/20 16:44
 */
@Component
@Order(Integer.MIN_VALUE)
public class ImoocAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE,
                SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_OPENID,
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                securityProperties.getBrowser().getSignInPage(),
                securityProperties.getBrowser().getSignUpUrl(),
                securityProperties.getBrowser().getSession().getSessionInvalidUrl()).permitAll();

        if (StringUtils.isNotBlank(securityProperties.getBrowser().getSignOutUrl())) {
            config.antMatchers(securityProperties.getBrowser().getSignOutUrl()).permitAll();
        }
        return false;
    }

}
