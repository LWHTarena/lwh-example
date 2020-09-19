package com.lwhtarena.rpc.core.registry;

import java.util.List;

/**
 * @author liwh
 * @Title: MulticastRegistry
 * @Package com.lwhtarena.rpc.core.registry
 * @Description: 广播方式注册中心,这里为了演示，不做具体实现
 * @Version 1.0.0
 * @date 2020/9/19 22:30
 */
public class MulticastRegistry implements Registry {

    public MulticastRegistry(String connectString) {

    }

    @Override
    public void register(Class<?> clazz, RegistryInfo registryInfo) {

    }

    @Override
    public List<RegistryInfo> fetchRegistry(Class<?> clazz) {
        return null;
    }

}

