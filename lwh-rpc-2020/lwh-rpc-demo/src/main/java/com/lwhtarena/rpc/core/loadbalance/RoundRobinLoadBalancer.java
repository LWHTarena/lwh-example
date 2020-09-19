package com.lwhtarena.rpc.core.loadbalance;

import com.lwhtarena.rpc.core.registry.RegistryInfo;

import java.util.List;

/**
 * @author liwh
 * @Title: RoundRobinLoadBalancer
 * @Package com.lwhtarena.rpc.core.loadbalance
 * @Description: 轮训的负载均衡组件
 * @Version 1.0.0
 * @date 2020/9/19 22:27
 */
public class RoundRobinLoadBalancer implements LoadBalancer {

    private RoundRobinLoadBalancer() {

    }

    @Override
    public RegistryInfo choose(List<RegistryInfo> registryInfos) {
        return null;
    }
}
