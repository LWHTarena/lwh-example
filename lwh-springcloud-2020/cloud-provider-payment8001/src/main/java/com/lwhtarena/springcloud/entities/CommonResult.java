package com.lwhtarena.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liwh
 * @Title: CommonResult
 * @Package com.lwhtarena.springcloud.entities
 * @Description: 结果 json
 * @Version 1.0.0
 * @date 2020/5/3 19:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this(code,message,null);
    }
}
