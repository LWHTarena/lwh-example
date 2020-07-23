package com.lwhtarena.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author liwh
 * @Title: SecurityConfig
 * @Package com.lwhtarena.config
 * @Description: 在接口上配置权限时使用
 * @Version 1.0.0
 * @date 2020/7/23 10:43
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(101)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
}

