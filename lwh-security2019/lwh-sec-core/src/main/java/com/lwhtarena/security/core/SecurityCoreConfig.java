package com.lwhtarena.security.core;

import com.lwhtarena.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author liwh
 * @Title: SecurityCoreConfig
 * @Package com.lwhtarena.security.core
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 16:03
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}
