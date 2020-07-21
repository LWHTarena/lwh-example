package com.lwhtarena.security.core.validate.code;

import com.lwhtarena.security.core.properties.SecurityConstants;

/**
 * @author liwh
 * @Title: ValidateCodeType
 * @Package com.lwhtarena.security.core.validate.code
 * @Description: 校验码类型
 * @Version 1.0.0
 * @date 2020/7/20 19:38
 */
public enum ValidateCodeType {

    /**
     * 短信验证码
     */
    SMS {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },
    /**
     * 图片验证码
     */
    IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };

    /**
     * 校验时从请求中获取的参数的名字
     * @return
     */
    public abstract String getParamNameOnValidate();

}