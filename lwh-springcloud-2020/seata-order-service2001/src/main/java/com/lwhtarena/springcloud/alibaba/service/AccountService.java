package com.lwhtarena.springcloud.alibaba.service;

import com.lwhtarena.springcloud.alibaba.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author liwh
 * @Title: AccountService
 * @Package com.lwhtarena.springcloud.alibaba.service
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/14 09:27
 */

@FeignClient(value = "seata-account-service")
public interface AccountService {
    @PostMapping(value = "/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
