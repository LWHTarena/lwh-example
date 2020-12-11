package com.lwhtarena.seata.pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liwh
 * @Title: ReduceBalanceRequestVO
 * @Package com.lwhtarena.seata.pay
 * @Description: 扣减余额请求 VO
 * @Version 1.0.0
 * @date 2020/12/9 09:41
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReduceBalanceRequestVO {

    private Long userId;

    private Integer price;
}
