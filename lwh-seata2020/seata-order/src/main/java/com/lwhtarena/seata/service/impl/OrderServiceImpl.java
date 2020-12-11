package com.lwhtarena.seata.service.impl;

import com.lwhtarena.seata.OperationResponse;
import com.lwhtarena.seata.dao.OrderDao;
import com.lwhtarena.seata.model.Order;
import com.lwhtarena.seata.model.OrderStatus;
import com.lwhtarena.seata.order.PlaceOrderRequestVO;
import com.lwhtarena.seata.pay.ReduceBalanceRequestVO;
import com.lwhtarena.seata.service.OrderService;
import com.lwhtarena.seata.storage.ReduceStockRequestVO;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author liwh
 * @Title: OrderServiceImpl
 * @Package com.lwhtarena.seata.service.impl
 * @Description:
 * @Version 1.0.0
 * @date 2020/12/9 10:19
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderDao orderDao;

    private final String STORAGE_SERVICE_HOST = "http://storage-service/storage";
    private final String PAY_SERVICE_HOST = "http://pay-service/pay";

    @GlobalTransactional
    @Override
    public OperationResponse placeOrder(PlaceOrderRequestVO placeOrderRequestVO) {
        Integer amount = 1;
        Integer price = placeOrderRequestVO.getPrice();

        Order order = Order.builder()
                .userId(placeOrderRequestVO.getUserId())
                .productId(placeOrderRequestVO.getProductId())
                .status(OrderStatus.INIT)
                .payAmount(price)
                .build();

        Integer saveOrderRecord = orderDao.saveOrder(order);
        log.info("保存订单{}", saveOrderRecord > 0 ? "成功" : "失败");
        log.info("当前 XID: {}", RootContext.getXID());
        // 扣减库存
        log.info("开始扣减库存");
        ReduceStockRequestVO reduceStockRequestVO = ReduceStockRequestVO.builder()
                .productId(placeOrderRequestVO.getProductId())
                .amount(amount)
                .build();
        String storageReduceUrl = String.format("%s/reduceStock", STORAGE_SERVICE_HOST);
        OperationResponse storageOperationResponse = restTemplate.postForObject(storageReduceUrl, reduceStockRequestVO, OperationResponse.class);
        log.info("扣减库存结果:{}", storageOperationResponse);

        // 扣减余额
        log.info("开始扣减余额");
        ReduceBalanceRequestVO reduceBalanceRequestVO = ReduceBalanceRequestVO.builder()
                .userId(placeOrderRequestVO.getUserId())
                .price(price)
                .build();

        String reduceBalanceUrl = String.format("%s/reduceBalance", PAY_SERVICE_HOST);
        OperationResponse balanceOperationResponse = restTemplate.postForObject(reduceBalanceUrl, reduceBalanceRequestVO, OperationResponse.class);
        log.info("扣减余额结果:{}", balanceOperationResponse);

        Integer updateOrderRecord = orderDao.updateOrder(order.getId(), OrderStatus.SUCCESS);
        log.info("更新订单:{} {}", order.getId(), updateOrderRecord > 0 ? "成功" : "失败");

        return OperationResponse.builder()
                .success(balanceOperationResponse.isSuccess() && storageOperationResponse.isSuccess())
                .build();
    }

}
