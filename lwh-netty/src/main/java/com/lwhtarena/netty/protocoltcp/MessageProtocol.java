package com.lwhtarena.netty.protocoltcp;

/**
 * @author liwh
 * @Title: MessageProtocol
 * @Package com.lwhtarena.netty.protocoltcp
 * @Description: 协议包
 * @Version 1.0.0
 * @date 2020/6/15 16:56
 */
public class MessageProtocol {
    private int len;
    private byte[] content;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
