package com.lwhtarena.seata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liwh
 * @Title: OperationResponse
 * @Package com.lwhtarena.seata
 * @Description: 操作返回结果
 * @Version 1.0.0
 * @date 2020/12/9 09:38
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationResponse {

    private boolean success;

    private String message;

    private Object data;
}
