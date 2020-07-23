package com.lwhtarena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

/**
 * @author liwh
 * @Title: Oauth2ClientApplication
 * @Package com.lwhtarena
 * @Description: 在启动类上添加@EnableOAuth2Sso注解来启用单点登录功能：
 * @Version 1.0.0
 * @date 2020/7/23 10:42
 */
@EnableOAuth2Sso
@SpringBootApplication
public class Oauth2ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ClientApplication.class, args);
    }

}

