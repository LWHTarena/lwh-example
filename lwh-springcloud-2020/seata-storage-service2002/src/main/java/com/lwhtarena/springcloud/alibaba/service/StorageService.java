package com.lwhtarena.springcloud.alibaba.service;

/**
 * @author liwh
 * @Title: StorageService
 * @Package com.lwhtarena.springcloud.alibaba.service
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/14 12:45
 */
public interface StorageService {
    /**
     * 扣减库存
     */
    void decrease(Long productId, Integer count);
}
