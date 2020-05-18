package com.lwhtarena.sc.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * @author liwh
 * @Title: Order
 * @Package com.lwhtarena.sc.model
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/17 23:10
 */
@ApiModel(value = "订单模块")
@Data
@AllArgsConstructor
public class Order {
    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "订单名称")
    private String name;

    public Order() {
        DecimalFormat df=new DecimalFormat("00000");
        this.orderNo="TAW"+df.format(new Random().nextInt(1000));
        this.name="订单"+new Random().nextInt(1000);
    }
}
