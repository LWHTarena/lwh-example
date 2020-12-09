package com.lwhtarena.oauth2.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @author liwh
 * @Title: AuthConfiguration
 * @Package com.lwhtarena.oauth2.security
 * @Description:
 * @Version 1.0.0
 * @date 2020/12/6 11:12
 */
@Configuration
@EnableAuthorizationServer
@Order(404)
public class AuthConfiguration extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private final NoOpPasswordEncoder noOpPasswordEncoder;

    public AuthConfiguration(AuthenticationManager authenticationManager, NoOpPasswordEncoder noOpPasswordEncoder) {
        this.authenticationManager = authenticationManager;
        this.noOpPasswordEncoder = noOpPasswordEncoder;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //密码模式（resource owner password credentials）
        clients.inMemory()
                .withClient("app1").secret(noOpPasswordEncoder.encode("123"))
                .authorizedGrantTypes("password")
                //.redirectUris("http://localhost:18010/webjars/oauth/oauth2.html")
                .scopes("read","write","reads","writes");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }
}
