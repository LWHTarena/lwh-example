package com.lwhtarena.api;

/**
 * @author liwh
 * @Title: IErrorCode
 * @Package com.lwhtarena.api
 * @Description: 封装API的错误码
 * @Version 1.0.0
 * @date 2020/7/23 12:50
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}

