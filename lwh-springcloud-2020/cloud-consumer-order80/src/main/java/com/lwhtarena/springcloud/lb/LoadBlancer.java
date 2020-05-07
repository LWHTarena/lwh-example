package com.lwhtarena.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author liwh
 * @Title: LoadBlancer
 * @Package com.lwhtarena.lb
 * @Description: 自定义均衡算法
 * @Version 1.0.0
 * @date 2020/5/7 09:03
 */
public interface LoadBlancer {

    /**
     * 收集服务器总共有多少台能够服务的机器，并在list里面
     * @param serviceInstances
     * @return
     */
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
