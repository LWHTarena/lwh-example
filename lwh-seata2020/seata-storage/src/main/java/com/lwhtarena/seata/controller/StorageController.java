package com.lwhtarena.seata.controller;

import com.lwhtarena.seata.OperationResponse;
import com.lwhtarena.seata.service.StorageService;
import com.lwhtarena.seata.storage.ReduceStockRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liwh
 * @Title: StorageController
 * @Package com.lwhtarena.seata.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/12/9 09:51
 */
@RestController
@RequestMapping("/storage")
@Slf4j
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/reduceStock")
    @ResponseBody
    public OperationResponse reduceStock(@RequestBody ReduceStockRequestVO reduceStockRequestVO) throws Exception {
        return storageService.reduceStock(reduceStockRequestVO);
    }
}
