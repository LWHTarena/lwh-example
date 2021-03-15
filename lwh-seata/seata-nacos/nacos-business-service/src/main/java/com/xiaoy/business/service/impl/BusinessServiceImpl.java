package com.xiaoy.business.service.impl;

import com.xiaoy.business.feign.OrderFeign;
import com.xiaoy.business.feign.StorageFeign;
import com.xiaoy.business.service.BusinessService;
import com.xiaoy.business.vo.OrderVo;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liwh
 * @since 2021-3-2 17:02
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private OrderFeign orderFeign;
    @Autowired
    private StorageFeign storageFeign;

    /***
     * 描述 业务入口 分布式事务
     * @param orderVo
     * @return  
     * @author liwh
     * @date 2021/3/15 23:34
     * @version 1.0
     **/
    @Override
    @GlobalTransactional
    public boolean business(OrderVo orderVo) {
        orderFeign.insertOrder(orderVo);
        try {
            Thread.sleep(10000);
        }catch (Exception e){

        }
        storageFeign.subStorage(orderVo.getCommodityCode(), orderVo.getCount());
        return true;
    }
}
