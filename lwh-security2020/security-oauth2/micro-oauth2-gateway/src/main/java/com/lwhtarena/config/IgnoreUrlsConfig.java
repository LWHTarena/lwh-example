package com.lwhtarena.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liwh
 * @Title: IgnoreUrlsConfig
 * @Package com.lwhtarena.config
 * @Description: 网关白名单配置
 * @Version 1.0.0
 * @date 2020/7/23 12:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Component
@ConfigurationProperties(prefix="secure.ignore")
public class IgnoreUrlsConfig {
    private List<String> urls;
}
