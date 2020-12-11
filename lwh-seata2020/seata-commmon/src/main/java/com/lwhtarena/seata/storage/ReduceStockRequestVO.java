package com.lwhtarena.seata.storage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liwh
 * @Title: ReduceStockRequestVO
 * @Package com.lwhtarena.seata.storage
 * @Description: 扣减库存请求 VO
 * @Version 1.0.0
 * @date 2020/12/9 09:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReduceStockRequestVO {

    private Long productId;

    private Integer amount;
}
