package com.lwhtarena.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liwh
 * @Title: Payment
 * @Package com.lwhtarena.springcloud.entities
 * @Description: 支付实体
 * @Version 1.0.0
 * @date 2020/5/3 19:38
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {

    private Long id;
    private String serial;
}
