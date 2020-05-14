package com.lwhtarena.springcloud.alibaba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liwh
 * @Title: CommonResult
 * @Package com.lwhtarena.springcloud.alibaba.domain
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/14 09:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String  message;
    private T       data;

    public CommonResult(Integer code, String message) {
        this(code,message,null);
    }
}
