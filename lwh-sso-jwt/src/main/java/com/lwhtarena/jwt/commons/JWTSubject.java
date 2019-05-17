package com.lwhtarena.jwt.commons;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * <blockquote><pre></pre></blockquote>
 * 作为Subject数据使用。也就是payload中保存的public claims
 * 其中不包含任何敏感数据
 * 开发中建议使用实体类型。或BO，DTO数据对象。
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class JWTSubject {

    private String username;

    public JWTSubject() {
        super();
    }

    public JWTSubject(String username) {
        super();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
