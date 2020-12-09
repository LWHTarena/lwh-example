package com.lwhtarena.oauth2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liwh
 * @Title: SecuryWebConfiguration
 * @Package com.lwhtarena.oauth2.security
 * @Description:
 * @Version 1.0.0
 * @date 2020/12/6 11:12
 */
@Configuration
@Order(300)
public class SecuryWebConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<String> permitAllResouces= new ArrayList<>();
        //Swagger资源
        permitAllResouces.addAll(Arrays.asList("/data/**","/oauth/**", "/login/**", "/logout/**","/test/**","/swaggger-ui.html","/doc.html", "/v2/**", "/swagger-resources", "/swagger-resources/**", "/webjars/**"));
        String[] resouceArray=permitAllResouces.toArray(new String[]{});
        http.csrf().disable();
        http
                .requestMatchers().antMatchers("/oauth/**","/login/**","/logout/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .and()
                .formLogin().permitAll()
                .and().authorizeRequests().mvcMatchers(resouceArray).permitAll(); //新增login form支持用户登录及授权
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("user").password("1234").roles("ADMIN");
    }
}
