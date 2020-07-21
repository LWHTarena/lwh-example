package com.lwhtarena.security.core.support;

/**
 * @author liwh
 * @Title: SimpleResponse
 * @Package com.lwhtarena.security.core.support
 * @Description: 简单响应的封装类
 * @Version 1.0.0
 * @date 2020/7/20 17:48
 */
public class SimpleResponse {

    public SimpleResponse(Object content){
        this.content = content;
    }

    private Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

}
