package com.lwhtarena.dubborpc.provider;

import com.lwhtarena.dubborpc.netty.NettyServer;

/**
 * @author liwh
 * @Title: ServerBootstrap
 * @Package com.lwhtarena.dubborpc.provider
 * @Description: 启动 --提供者，就是NettyServer
 * @Version 1.0.0
 * @date 2020/6/16 03:25
 */
public class ServerBootstrap {
    public static void main(String[] args) {
        //todo
        NettyServer.starServer("127.0.0.1",7000);
    }
}
