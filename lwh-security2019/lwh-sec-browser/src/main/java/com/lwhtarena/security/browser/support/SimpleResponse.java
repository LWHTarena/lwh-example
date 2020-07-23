package com.lwhtarena.security.browser.support;

/**
 * @author liwh
 * @Title: SimpleResponse
 * @Package com.lwhtarena.security.browser.support
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 12:29
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
