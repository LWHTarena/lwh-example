package com.lwhtarena.security.browser.authorize;

import com.lwhtarena.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author liwh
 * @Title: BrowserAuthorizeConfigProvider
 * @Package com.lwhtarena.security.browser.authorize
 * @Description: 浏览器环境默认的授权配置，对常见的静态资源，如js,css，图片等不验证身份
 * @Version 1.0.0
 * @date 2020/7/20 22:09
 */
@Component
@Order(Integer.MIN_VALUE)
public class BrowserAuthorizeConfigProvider implements AuthorizeConfigProvider {

    /* (non-Javadoc)
     * @see com.imooc.security.core.authorize.AuthorizeConfigProvider#config(org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry)
     */
    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(HttpMethod.GET,
                "/**/*.js",
                "/**/*.css",
                "/**/*.jpg",
                "/**/*.png",
                "/**/*.gif").permitAll();
        return false;
    }

}
