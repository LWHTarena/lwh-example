package com.lwhtarena.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author liwh
 * @Title: SecurityCoreConfig
 * @Package com.lwhtarena.security
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 12:34
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}
