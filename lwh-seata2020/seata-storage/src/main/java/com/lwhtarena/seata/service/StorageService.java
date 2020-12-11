package com.lwhtarena.seata.service;

import com.lwhtarena.seata.OperationResponse;
import com.lwhtarena.seata.storage.ReduceStockRequestVO;

/**
 * @author liwh
 * @Title: StorageService
 * @Package com.lwhtarena.seata.service
 * @Description:
 * @Version 1.0.0
 * @date 2020/12/9 09:52
 */
public interface StorageService {
    /**
     * 扣减库存
     *
     * @param reduceStockRequestVO 请求 VO
     * @return 操作结果
     * @throws Exception 扣减失败时抛出异常
     */
    OperationResponse reduceStock(ReduceStockRequestVO reduceStockRequestVO) throws Exception;
}

