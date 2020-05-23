package com.lwhtarena.springmvc.service;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author liwh
 * @Title: DeferredResultQueue
 * @Package com.lwhtarena.springmvc.service
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/23 13:11
 */
public class DeferredResultQueue {

    private static Queue<DeferredResult<Object>> queue = new ConcurrentLinkedQueue<DeferredResult<Object>>();

    public static void save(DeferredResult<Object> deferredResult) {
        queue.add(deferredResult);
    }

    public static DeferredResult<Object> get() {
        return queue.poll();
    }
}


