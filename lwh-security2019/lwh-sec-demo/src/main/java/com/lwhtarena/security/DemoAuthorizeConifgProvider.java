package com.lwhtarena.security;

import com.lwhtarena.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author liwh
 * @Title: DemoAuthorizeConifgProvider
 * @Package com.lwhtarena.security
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 22:26
 */
@Component
public class DemoAuthorizeConifgProvider implements AuthorizeConfigProvider {

    /* (non-Javadoc)
     * @see com.imooc.security.core.authorize.AuthorizeConfigProvider#config(org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry)
     */
    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        //demo项目授权配置
        return false;
    }

}
