package com.lwhtarena.springcloud.alibaba.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author liwh
 * @Title: AccountService
 * @Package com.lwhtarena.springcloud.alibaba.service
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/14 12:59
 */
public interface AccountService {
    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金额
     */
    void decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
