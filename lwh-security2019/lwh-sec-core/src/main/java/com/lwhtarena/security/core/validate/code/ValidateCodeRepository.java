package com.lwhtarena.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author liwh
 * @Title: ValidateCodeRepository
 * @Package com.lwhtarena.security.core.validate.code
 * @Description: 校验码存取器
 * @Version 1.0.0
 * @date 2020/7/20 19:36
 */
public interface ValidateCodeRepository {

    /**
     * 保存验证码
     * @param request
     * @param code
     * @param validateCodeType
     */
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);
    /**
     * 获取验证码
     * @param request
     * @param validateCodeType
     * @return
     */
    ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType);
    /**
     * 移除验证码
     * @param request
     * @param codeType
     */
    void remove(ServletWebRequest request, ValidateCodeType codeType);

}
