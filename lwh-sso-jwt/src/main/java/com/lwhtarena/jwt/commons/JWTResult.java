package com.lwhtarena.jwt.commons;

import io.jsonwebtoken.Claims;

/**
 * <p>
 * <h2>简述：结果对象。</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * <blockquote><pre></pre></blockquote>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class JWTResult {

    /**
     * 错误编码。在JWTUtils中定义的常量。
     * 200为正确
     */
    private int errCode;

    /**
     * 是否成功，代表结果的状态。
     */
    private boolean success;

    /**
     * 验证过程中payload中的数据。
     */
    private Claims claims;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }
}
