package com.lwhtarena.seata.service;

import com.lwhtarena.seata.OperationResponse;
import com.lwhtarena.seata.pay.ReduceBalanceRequestVO;

/**
 * @author liwh
 * @Title: PayService
 * @Package com.lwhtarena.seata.service
 * @Description:
 * @Version 1.0.0
 * @date 2020/12/9 10:08
 */
public interface PayService {
    /**
     * @param reduceBalanceRequestVO
     * @return
     * @throws Exception
     */
    OperationResponse reduceBalance(ReduceBalanceRequestVO reduceBalanceRequestVO) throws Exception;
}
