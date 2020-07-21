package com.lwhtarena.security.core.validate.code;

import com.lwhtarena.security.core.properties.SecurityProperties;
import com.lwhtarena.security.core.validate.code.image.ImageCodeGenerator;
import com.lwhtarena.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.lwhtarena.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liwh
 * @Title: ValidateCodeBeanConfig
 * @Package com.lwhtarena.security.core.validate.code
 * @Description:
 *        验证码相关的扩展点配置。配置在这里的bean，业务系统都可以通过声明同类型或同名的bean来覆盖安全模块默认的配置。
 * @Version 1.0.0
 * @date 2020/7/20 19:31
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 图片验证码图片生成器
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    /**
     * 短信验证码发送器
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }

}
