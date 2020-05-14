package com.lwhtarena.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author liwh
 * @Title: AccountDao
 * @Package com.lwhtarena.springcloud.alibaba.dao
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/14 12:58
 */
@Mapper
public interface AccountDao {
    /**
     * 扣减账户余额
     */
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
