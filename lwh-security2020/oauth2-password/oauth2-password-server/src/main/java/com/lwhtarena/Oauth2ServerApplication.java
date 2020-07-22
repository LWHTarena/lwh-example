package com.lwhtarena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liwh
 * @Title: Oauth2ServerApplication
 * @Package com.lwhtarena
 * @Description:
 *
 * 授权码模式使用
 * 启动oauth2-server服务；
 * 在浏览器访问该地址进行登录授权：http://localhost:9401/oauth/authorize?response_type=code&client_id=admin&redirect_uri=http://www.baidu.com&scope=all
 * 输入账号密码进行登录操作：
 *
 * @Version 1.0.0
 * @date 2020/7/22 22:47
 */
@SpringBootApplication
public class Oauth2ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerApplication.class,args);
    }
}
