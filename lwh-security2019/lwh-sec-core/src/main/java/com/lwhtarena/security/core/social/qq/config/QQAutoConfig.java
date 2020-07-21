package com.lwhtarena.security.core.social.qq.config;

import com.lwhtarena.security.core.properties.QQProperties;
import com.lwhtarena.security.core.properties.SecurityProperties;
import com.lwhtarena.security.core.social.qq.connet.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author liwh
 * @Title: QQAutoConfig
 * @Package com.lwhtarena.security.core.social.qq.config
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 17:00
 */
@Configuration
@ConditionalOnProperty(prefix = "imooc.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter
     * #createConnectionFactory()
     */
    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
    }

}
