
## 网页单点登录演示

1、修改oauth2-jwt-server模块中的AuthorizationServerConfig类，
将绑定的跳转路径为http://localhost:9501/login，并添加获取秘钥时的身份认证。

2、启动oauth2-client服务和oauth2-jwt-server服务；
  
访问客户端需要授权的接口http://localhost:9501/user/getCurrentUser会跳转到授权服务的登录界面；

![](./img/001.png)

3、进行登录操作后跳转到授权页面；

![](./img/003.png)

4、授权后会跳转到原来需要权限的接口地址，展示登录用户信息；

![](./img/002.png)

5、如果需要跳过授权操作进行自动授权可以添加autoApprove(true)配置：
```java
/**
 * 认证服务器配置
 * Created by macro on 2019/9/30.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    //以上省略一堆代码...
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("admin")
                .secret(passwordEncoder.encode("admin123456"))
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(864000)
//                .redirectUris("http://www.baidu.com")
                .redirectUris("http://localhost:9501/login") //单点登录时配置
                .autoApprove(true) //自动授权配置
                .scopes("all")
                .authorizedGrantTypes("authorization_code","password","refresh_token");
    }
}
```


## 调用接口单点登录演示

这里我们使用Postman来演示下如何使用正确的方式调用需要登录的客户端接口。

1、访问客户端需要登录的接口：http://localhost:9501/user/getCurrentUser

2、使用Oauth2认证方式获取访问令牌：

![](./img/004.png)

3、输入获取访问令牌的相关信息，点击请求令牌：

![](./img/005.png)

4、此时会跳转到认证服务器进行登录操作：

![](./img/006.png)

5、登录成功后使用获取到的令牌：

6、最后请求接口可以获取到如下信息：
```json
{
    "authorities": [
        {
            "authority": "admin"
        }
    ],
    "details": {
        "remoteAddress": "0:0:0:0:0:0:0:1",
        "sessionId": "63BB793E35383B2FFC608333B3BF4988",
        "tokenValue": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJtYWNybyIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE1NzI2OTAxNzUsImF1dGhvcml0aWVzIjpbImFkbWluIl0sImp0aSI6IjIwN2U5MTQzLTVjNTUtNDhkMS1iZmU3LTgwMzUyZTQ3Y2QyZCIsImNsaWVudF9pZCI6ImFkbWluIiwiZW5oYW5jZSI6ImVuaGFuY2UgaW5mbyJ9.xf3cBu9yCm0sME0j3UcP53FwF4tJVJC5aJbEj_Y2XcU",
        "tokenType": "bearer",
        "decodedDetails": null
    },
    "authenticated": true,
    "userAuthentication": {
        "authorities": [
            {
                "authority": "admin"
            }
        ],
        "details": null,
        "authenticated": true,
        "principal": "macro",
        "credentials": "N/A",
        "name": "macro"
    },
    "clientOnly": false,
    "oauth2Request": {
        "clientId": "admin",
        "scope": [
            "all"
        ],
        "requestParameters": {
            "client_id": "admin"
        },
        "resourceIds": [],
        "authorities": [],
        "approved": true,
        "refresh": false,
        "redirectUri": null,
        "responseTypes": [],
        "extensions": {},
        "grantType": null,
        "refreshTokenRequest": null
    },
    "principal": "macro",
    "credentials": "",
    "name": "macro"
}
```

## oauth2-client添加权限校验

1、添加配置开启基于方法的权限校验：
```java
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(101)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
}
```

2、在UserController中添加需要admin权限的接口：
```java
@RestController
@RequestMapping("/user")
public class UserController {

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/auth/admin")
    public Object adminAuth() {
        return "Has admin auth!";
    }

}
```

3、访问需要admin权限的接口：http://localhost:9501/user/auth/admin

![](./img/007.png)

4、使用没有admin权限的帐号，比如andy:123456获取令牌后访问该接口，会发现没有权限访问。

![](./img/008.png)

  
