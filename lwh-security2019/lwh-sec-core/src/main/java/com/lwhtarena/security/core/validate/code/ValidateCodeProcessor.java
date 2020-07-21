package com.lwhtarena.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author liwh
 * @Title: ValidateCodeProcessor
 * @Package com.lwhtarena.security.core.validate.code
 * @Description: 校验码处理器，封装不同校验码的处理逻辑
 * @Version 1.0.0
 * @date 2020/7/20 19:35
 */
public interface ValidateCodeProcessor {

    /**
     * 创建校验码
     *
     * @param request
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     *
     * @param servletWebRequest
     * @throws Exception
     */
    void validate(ServletWebRequest servletWebRequest);

}
