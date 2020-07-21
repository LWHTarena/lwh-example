package com.lwhtarena.security.app.social;

import com.lwhtarena.security.core.properties.SecurityConstants;
import com.lwhtarena.security.core.social.support.ImoocSpringSocialConfigurer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author liwh
 * @Title: SpringSocialConfigurerPostProcessor
 * @Package com.lwhtarena.security.app.social
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 20:58
 */
@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(StringUtils.equals(beanName, "imoocSocialSecurityConfig")){
            ImoocSpringSocialConfigurer config = (ImoocSpringSocialConfigurer)bean;
            config.signupUrl(SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL);
            return config;
        }
        return bean;
    }

}
