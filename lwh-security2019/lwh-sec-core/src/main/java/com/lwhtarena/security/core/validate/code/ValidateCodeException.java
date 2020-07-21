package com.lwhtarena.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author liwh
 * @Title: ValidateCodeException
 * @Package com.lwhtarena.security.core.validate.code
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 19:33
 */
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException(String msg) {
        super(msg);
    }

}
