package com.lwhtarena.rpc.core.loadbalance;

import com.lwhtarena.rpc.core.registry.RegistryInfo;

import java.util.List;

/**
 * @author liwh
 * @Title: LoadBalancer
 * @Package com.lwhtarena.rpc.core.loadbalance
 * @Description: 负载均衡组件
 * @Version 1.0.0
 * @date 2020/9/19 22:26
 */
public interface LoadBalancer {

    /**
     * 选择一个生产者
     *
     * @param registryInfos 生产者列表
     * @return 选中的生产者
     */
    RegistryInfo choose(List<RegistryInfo> registryInfos);

}
