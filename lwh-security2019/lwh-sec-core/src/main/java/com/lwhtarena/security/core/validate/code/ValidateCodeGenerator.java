package com.lwhtarena.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author liwh
 * @Title: ValidateCodeGenerator
 * @Package com.lwhtarena.security.core.validate.code
 * @Description: 校验码生成器
 * @Version 1.0.0
 * @date 2020/7/20 19:34
 */
public interface ValidateCodeGenerator {

    /**
     * 生成校验码
     * @param request
     * @return
     */
    ValidateCode generate(ServletWebRequest request);

}
