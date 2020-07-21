package com.lwhtarena.security.app;

/**
 * @author liwh
 * @Title: AppSecretException
 * @Package com.lwhtarena.security.app
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 20:54
 */
public class AppSecretException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -1629364510827838114L;

    public AppSecretException(String msg){
        super(msg);
    }

}
