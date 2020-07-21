package com.lwhtarena.security.core.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @author liwh
 * @Title: AuthorizeConfigManager
 * @Package com.lwhtarena.security.core.authorize
 * @Description:
 * 授权信息管理器
 *
 * 用于收集系统中所有 AuthorizeConfigProvider 并加载其配置
 * @Version 1.0.0
 * @date 2020/7/20 16:42
 */
public interface AuthorizeConfigManager {

    /**
     * @param config
     */
    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);

}
