package com.lwhtarena.springcloud.alibaba.controller;

import com.lwhtarena.springcloud.alibaba.domain.CommonResult;
import com.lwhtarena.springcloud.alibaba.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liwh
 * @Title: StorageController
 * @Package com.lwhtarena.springcloud.alibaba.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/14 12:48
 */
@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    /**
     * 扣减库存
     */
    @RequestMapping("/storage/decrease")
    public CommonResult decrease(Long productId, Integer count) {
        storageService.decrease(productId, count);
        return new CommonResult(200,"扣减库存成功！");
    }
}
