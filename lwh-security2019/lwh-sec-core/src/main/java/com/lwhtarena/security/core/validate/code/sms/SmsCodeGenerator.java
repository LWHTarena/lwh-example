package com.lwhtarena.security.core.validate.code.sms;

import com.lwhtarena.security.core.properties.SecurityProperties;
import com.lwhtarena.security.core.validate.code.ValidateCode;
import com.lwhtarena.security.core.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author liwh
 * @Title: SmsCodeGenerator
 * @Package com.lwhtarena.security.core.validate.code.sms
 * @Description: 短信验证码生成器
 * @Version 1.0.0
 * @date 2020/7/20 20:44
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.imooc.security.core.validate.code.ValidateCodeGenerator#generate(org.
     * springframework.web.context.request.ServletWebRequest)
     */
    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }



}
