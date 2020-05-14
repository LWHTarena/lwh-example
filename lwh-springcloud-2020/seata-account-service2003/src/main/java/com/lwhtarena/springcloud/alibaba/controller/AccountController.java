package com.lwhtarena.springcloud.alibaba.controller;

import com.lwhtarena.springcloud.alibaba.domain.CommonResult;
import com.lwhtarena.springcloud.alibaba.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author liwh
 * @Title: AccountController
 * @Package com.lwhtarena.springcloud.alibaba.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/14 13:02
 */
@RestController
public class AccountController {

    @Resource
    AccountService accountService;

    /**
     * 扣减账户余额
     */
    @RequestMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money){
        accountService.decrease(userId,money);
        return new CommonResult(200,"扣减账户余额成功！");
    }
}
