package com.lwhtarena.seata.service.impl;

import com.lwhtarena.seata.OperationResponse;
import com.lwhtarena.seata.dao.ProductDao;
import com.lwhtarena.seata.service.StorageService;
import com.lwhtarena.seata.storage.ReduceStockRequestVO;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liwh
 * @Title: StorageServiceImpl
 * @Package com.lwhtarena.seata.service.impl
 * @Description:
 * @Version 1.0.0
 * @date 2020/12/9 09:53
 */

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Autowired
    private ProductDao productDao;

    @Override
    public OperationResponse reduceStock(ReduceStockRequestVO reduceStockRequestVO) throws Exception {
        log.info("当前 XID: {}", RootContext.getXID());

        // 检查库存
        checkStock(reduceStockRequestVO.getProductId(), reduceStockRequestVO.getAmount());

        log.info("开始扣减 {} 库存", reduceStockRequestVO.getProductId());
        // 扣减库存
        Integer record = productDao.reduceStock(reduceStockRequestVO.getProductId(), reduceStockRequestVO.getAmount());
        log.info("扣减 {} 库存结果:{}", reduceStockRequestVO.getProductId(), record > 0 ? "操作成功" : "扣减库存失败");

        return OperationResponse.builder()
                .success(record > 0)
                .message(record > 0 ? "操作成功" : "扣减库存失败")
                .build();

    }

    public void checkStock(Long productId, Integer requiredAmount) throws Exception {
        log.info("检查 {} 库存", productId);
        Integer stock = productDao.getStock(productId);

        if (stock < requiredAmount) {
            log.warn("{} 库存不足，当前库存:{}", productId, stock);
            throw new Exception("库存不足");
        }
    }
}
