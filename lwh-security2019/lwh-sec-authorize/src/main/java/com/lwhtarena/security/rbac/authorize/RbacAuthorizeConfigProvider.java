package com.lwhtarena.security.rbac.authorize;

import com.lwhtarena.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author liwh
 * @Title: RbacAuthorizeConfigProvider
 * @Package com.lwhtarena.security.rbac.authorize
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 21:17
 */
@Component
@Order(Integer.MAX_VALUE)
public class RbacAuthorizeConfigProvider implements AuthorizeConfigProvider {

    /* (non-Javadoc)
     * @see com.imooc.security.core.authorize.AuthorizeConfigProvider#config(org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry)
     */
    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config
                .antMatchers(HttpMethod.GET, "/fonts/**").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/**/*.html",
                        "/admin/me",
                        "/resource").authenticated()
                .anyRequest()
                .access("@rbacService.hasPermission(request, authentication)");
        return true;
    }

}
