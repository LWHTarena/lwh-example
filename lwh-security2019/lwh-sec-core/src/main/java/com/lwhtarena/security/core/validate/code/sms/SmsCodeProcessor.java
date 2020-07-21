package com.lwhtarena.security.core.validate.code.sms;

import com.lwhtarena.security.core.properties.SecurityConstants;
import com.lwhtarena.security.core.validate.code.ValidateCode;
import com.lwhtarena.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author liwh
 * @Title: SmsCodeProcessor
 * @Package com.lwhtarena.security.core.validate.code.sms
 * @Description: 短信验证码处理器
 * @Version 1.0.0
 * @date 2020/7/20 20:45
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
        smsCodeSender.send(mobile, validateCode.getCode());
    }

}
