package com.lwhtarena.security.server;

import com.lwhtarena.security.app.authentication.openid.OpenIdAuthenticationSecurityConfig;
import com.lwhtarena.security.core.authentication.FormAuthenticationConfig;
import com.lwhtarena.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.lwhtarena.security.core.authorize.AuthorizeConfigManager;
import com.lwhtarena.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author liwh
 * @Title: ImoocResourceServerConfig
 * @Package com.lwhtarena.security.server
 * @Description: 资源服务器配置
 * @Version 1.0.0
 * @date 2020/7/20 20:51
 */
@Configuration
@EnableResourceServer
public class ImoocResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    protected AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler imoocAuthenticationFailureHandler;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer imoocSocialSecurityConfig;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        formAuthenticationConfig.configure(http);

        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(imoocSocialSecurityConfig)
                .and()
                .apply(openIdAuthenticationSecurityConfig)
                .and()
                .csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());
    }
}
