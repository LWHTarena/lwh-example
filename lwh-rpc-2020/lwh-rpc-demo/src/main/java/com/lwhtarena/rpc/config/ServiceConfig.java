package com.lwhtarena.rpc.config;

/**
 * @author liwh
 * @Title: ServiceConfig
 * @Package com.lwhtarena.rpc
 * @Description: 生产者配置
 * @Version 1.0.0
 * @date 2020/9/19 22:22
 */
public class ServiceConfig<T> {

    public Class<T> type;

    public T instance;

    public ServiceConfig(Class<T> type, T instance) {
        this.type = type;
        this.instance = instance;
    }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    public T getInstance() {
        return instance;
    }

    public void setInstance(T instance) {
        this.instance = instance;
    }
}

