package com.lwhtarena.lettuce;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;
import io.lettuce.core.resource.ClientResources;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/04/18 17:16:46
 * @description 单机模式下代码测试
 */
public class BasicUsage {
    public static void main(String[] args) {
        // client
        RedisClient client = RedisClient.create("redis://192.168.56.15");

        // connection, 线程安全的长连接，连接丢失时会自动重连，直到调用 close 关闭连接。
        StatefulRedisConnection<String, String> connection = client.connect();

        // sync, 默认超时时间为 60s.
        RedisStringCommands<String, String> sync = connection.sync();
        sync.set("host", "note.abeffect.com");
        String value = sync.get("host");
        System.out.println(value);

        // close connection
        connection.close();

        // shutdown
        client.shutdown();
    }
}
