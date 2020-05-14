package com.lwhtarena.springcloud.alibaba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liwh
 * @Title: Storage
 * @Package com.lwhtarena.springcloud.alibaba.domain
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/14 12:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage implements Serializable {
    private Long id;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 总库存
     */
    private Integer total;

    /**
     * 已用库存
     */
    private Integer used;

    /**
     * 剩余库存
     */
    private Integer residue;
}
