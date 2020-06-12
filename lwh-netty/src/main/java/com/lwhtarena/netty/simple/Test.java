package com.lwhtarena.netty.simple;

import io.netty.util.NettyRuntime;

/**
 * @author liwh
 * @Title: Test
 * @Package com.lwhtarena.netty.simple
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/10 18:39
 */
public class Test {
    public static void main(String[] args) {
        //查看系统核数
        /**
         *  private static final int DEFAULT_EVENT_LOOP_THREADS = Math.max(1, SystemPropertyUtil.getInt("io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
         */
        System.out.println(NettyRuntime.availableProcessors());
    }
}
