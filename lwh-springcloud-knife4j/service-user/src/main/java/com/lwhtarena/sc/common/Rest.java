package com.lwhtarena.sc.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liwh
 * @Title: Rest
 * @Package com.lwhtarena.sc.common
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/17 23:25
 */
@Data
public class Rest<T> {
    @ApiModelProperty(value = "是否成功")
    private boolean success=true;
    @ApiModelProperty(value = "返回对象")
    private T data;
    @ApiModelProperty(value = "错误编号")
    private Integer errCode;
    @ApiModelProperty(value = "错误信息")
    private String message;
}
