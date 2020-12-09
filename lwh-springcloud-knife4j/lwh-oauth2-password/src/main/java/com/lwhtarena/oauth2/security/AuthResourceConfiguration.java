package com.lwhtarena.oauth2.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author liwh
 * @Title: AuthResourceConfiguration
 * @Package com.lwhtarena.oauth2.security
 * @Description:
 * @Version 1.0.0
 * @date 2020/12/6 11:12
 */
@Configuration
@EnableResourceServer
@Order(402)
public class AuthResourceConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().requestMatchers().antMatchers("/api/**");
    }
}

