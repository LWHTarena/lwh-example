package com.lwhtarena.rpc.config;

/**
 * @author liwh
 * @Title: ReferenceConfig
 * @Package com.lwhtarena.rpc.config
 * @Description: 消费者配置
 * @Version 1.0.0
 * @date 2020/9/19 22:22
 */

public class ReferenceConfig<T> {

    private Class<T> type;

    public ReferenceConfig(Class<T> type) {
        this.type = type;
    }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }
}