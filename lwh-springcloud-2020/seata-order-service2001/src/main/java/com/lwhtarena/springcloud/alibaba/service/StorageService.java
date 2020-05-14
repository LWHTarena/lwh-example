package com.lwhtarena.springcloud.alibaba.service;

import com.lwhtarena.springcloud.alibaba.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liwh
 * @Title: StorageService
 * @Package com.lwhtarena.springcloud.alibaba.service
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/14 09:28
 */
@FeignClient(value = "seata-storage-service")
public interface StorageService {
    @PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
