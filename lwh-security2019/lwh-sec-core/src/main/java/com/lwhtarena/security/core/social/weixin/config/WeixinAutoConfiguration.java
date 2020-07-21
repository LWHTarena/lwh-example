package com.lwhtarena.security.core.social.weixin.config;

import com.lwhtarena.security.core.properties.SecurityProperties;
import com.lwhtarena.security.core.properties.WeixinProperties;
import com.lwhtarena.security.core.social.view.ImoocConnectView;
import com.lwhtarena.security.core.social.weixin.connect.WeixinConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;


/**
 * @author liwh
 * @Title: WeixinAutoConfiguration
 * @Package com.lwhtarena.security.core.social.weixin.config
 * @Description: 微信登录配置
 * @Version 1.0.0
 * @date 2020/7/20 17:15
 */
@Configuration
@ConditionalOnProperty(prefix = "imooc.security.social.weixin", name = "app-id")
public class WeixinAutoConfiguration extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    public WeixinAutoConfiguration() {
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter
     * #createConnectionFactory()
     */
    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        WeixinProperties weixinConfig = securityProperties.getSocial().getWeixin();
        return new WeixinConnectionFactory(weixinConfig.getProviderId(), weixinConfig.getAppId(),
                weixinConfig.getAppSecret());
    }

    @Bean({"connect/weixinConnect", "connect/weixinConnected"})
    @ConditionalOnMissingBean(name = "weixinConnectedView")
    public View weixinConnectedView() {
        return new ImoocConnectView();
    }

}
