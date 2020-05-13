package com.lwhtarena.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lwhtarena.springcloud.entities.CommonResult;

/**
 * @author liwh
 * @Title: CustomerBlockHandler
 * @Package com.lwhtarena.springcloud.alibaba.myhandler
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/13 10:06
 */
public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(4444,"按客戶自定义,global handlerException----1");
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(4444,"按客戶自定义,global handlerException----2");
    }
}
