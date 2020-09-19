package com.lwhtarena.rpc.core.loadbalance;

import com.lwhtarena.rpc.core.registry.RegistryInfo;

import java.util.List;
import java.util.Random;

/**
 * @author liwh
 * @Title: RandomLoadbalancer
 * @Package com.lwhtarena.rpc.core.loadbalance
 * @Description: 随机负载均衡
 * @Version 1.0.0
 * @date 2020/9/19 22:27
 */
public class RandomLoadbalancer implements LoadBalancer {
    @Override
    public RegistryInfo choose(List<RegistryInfo> registryInfos) {
        Random random = new Random();
        int index = random.nextInt(registryInfos.size());
        return registryInfos.get(index);
    }
}

