package com.lwhtarena.seata.controller;

import com.lwhtarena.seata.OperationResponse;
import com.lwhtarena.seata.pay.ReduceBalanceRequestVO;
import com.lwhtarena.seata.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liwh
 * @Title: PayController
 * @Package com.lwhtarena.seata.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/12/9 10:07
 */
@RestController
@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private PayService payService;

    @PostMapping("/reduceBalance")
    @ResponseBody
    public OperationResponse reduceBalance(@RequestBody ReduceBalanceRequestVO reduceBalanceRequestVO) throws Exception {
        return payService.reduceBalance(reduceBalanceRequestVO);
    }

}
