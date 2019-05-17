package com.lwhtarena.security.aes.service;

import com.lwhtarena.security.aes.utils.AesResponse;

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
public interface AesService {
    public AesResponse testAes(String key, String securityMessage, String name, String password);
}
