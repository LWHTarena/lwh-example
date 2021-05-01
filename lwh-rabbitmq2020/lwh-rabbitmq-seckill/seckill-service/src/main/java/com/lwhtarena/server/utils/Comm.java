package com.lwhtarena.server.utils;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/05/01 15:52:29
 * @description
 */
public class Comm {
    /**秒杀成功异步发送邮件的消息模型**/
    public static String EMAIL_QUEUE ="kill.item.success.email.queue";
    public static String EMAIL_EXCHANGE ="kill.item.success.email.exchange";
    public static String EMAIL_ROUTING_KEY="kill.item.success.kill.dead.routing.key";

    /**订单超时未支付自动失效-死信队列消息模型**/
    public static String DEAD_QUEUE="kill.item.success.kill.dead.queue";
    public static String DEAD_EXCHANGE="kill.item.success.kill.dead.exchange";
    public static String DEAD_ROUTING_KEY="kill.item.success.kill.dead.routing.key";
    public static String DEAD_REAL_QUEUE="kill.item.success.kill.dead.real.queue";
    public static String DEAD_PROD_EXCHANGE="kill.item.success.kill.dead.prod.exchange";
    public static String DEAD_PROD_ROUTING_KEY="kill.item.success.kill.dead.prod.routing.key";
}
