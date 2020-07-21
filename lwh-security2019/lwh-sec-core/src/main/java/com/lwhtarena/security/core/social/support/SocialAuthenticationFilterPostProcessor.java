package com.lwhtarena.security.core.social.support;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * @author liwh
 * @Title: SocialAuthenticationFilterPostProcessor
 * @Package com.lwhtarena.security.core.social.support
 * @Description: SocialAuthenticationFilter后处理器，用于在不同环境下个性化社交登录的配置
 * @Version 1.0.0
 * @date 2020/7/20 17:07
 */
public interface SocialAuthenticationFilterPostProcessor {

    /**
     * @param socialAuthenticationFilter
     */
    void process(SocialAuthenticationFilter socialAuthenticationFilter);

}
