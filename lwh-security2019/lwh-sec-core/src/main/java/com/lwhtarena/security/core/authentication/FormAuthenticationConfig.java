package com.lwhtarena.security.core.authentication;

import com.lwhtarena.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author liwh
 * @Title: FormAuthenticationConfig
 * @Package com.lwhtarena.security.core.authentication
 * @Description: 表单登录配置
 * @Version 1.0.0
 * @date 2020/7/20 16:32
 */
@Component
public class FormAuthenticationConfig {

    @Autowired
    protected AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler imoocAuthenticationFailureHandler;

    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
                .successHandler(imoocAuthenticationSuccessHandler)
                .failureHandler(imoocAuthenticationFailureHandler);
    }

}
