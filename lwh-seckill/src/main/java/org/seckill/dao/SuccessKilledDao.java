package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

/**
 * @author liwh
 * @Title: SuccessKilledDao
 * @Package org.seckill.dao
 * @Description:
 * @Version 1.0.0
 * @date 2020/8/5 22:59
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细，可过滤重复(数据表success_killed使用联合主键)
     *
     * @param seckillId
     * @param userPhone
     * @return 插入行数
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 根据id查询SuccessKilled并携带秒杀商品对象实体
     *
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
