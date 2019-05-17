package com.lwhtarena.security.des.service;

import com.lwhtarena.security.des.utils.DesResponse;

/**
 * <p>
 * <h2>简述：</h2>
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
public interface DesService {
    public DesResponse testDes(String key, String securityMessage, String name, String password);
}
