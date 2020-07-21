package com.lwhtarena.security.core.social.qq.connet;

import com.lwhtarena.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author liwh
 * @Title: QQConnectionFactory
 * @Package com.lwhtarena.security.core.social.qq.connet
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 16:48
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }

}
