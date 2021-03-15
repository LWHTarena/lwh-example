package cn.itcast.order.web;

import cn.itcast.order.entity.Order;
import cn.itcast.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liwh
 */
@RestController
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GlobalTransactional
    @PostMapping
    public ResponseEntity<Long> createOrder(Order order){
        Long orderId = orderService.create(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }

    @GlobalTransactional
    @GetMapping
    public ResponseEntity<Long> createOrder1(){
        Order order =new Order();
        order.setUserId("user202003032042012");
        order.setCommodityCode("100202003032041");
        order.setCount(2);
        order.setMoney(5);
        Long orderId = orderService.create(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }
}
