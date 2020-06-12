package com.lwhtarena.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author liwh
 * @Title: NettyServerHandler
 * @Package com.lwhtarena.netty.simple
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/10 12:12
 */

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取数据实际
     * @param ctx 上下文
     * @param msg 客户端发送的信息
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        /**
         * 比如这里我们有非常耗时的业务-> 异步执行 -> 提交channel对应的
         * NIOEventLoop 的 taskQuenue中
         */
        /*解决方案一：用户自定义的普通任务*/
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("您好，客户端！已接收到的您的信息~",CharsetUtil.UTF_8));
                    System.out.println("channel code ="+ctx.channel().hashCode());
                }catch (Exception e){
                    System.out.println("发生异常"+e.getMessage());
                }
            }
        });
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(20);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("您好，客户端！再次确认已接收到的您的信息~",CharsetUtil.UTF_8));
                    System.out.println("channel code ="+ctx.channel().hashCode());
                }catch (Exception e){
                    System.out.println("发生异常"+e.getMessage());
                }
            }
        });

        /*解决方案二：用户自定义定时任务，-> 该任务提交到scheduleTaskQueue中*/
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(20);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("您好，客户端！该任务提交到scheduleTaskQueue中~",CharsetUtil.UTF_8));
                    System.out.println("channel code ="+ctx.channel().hashCode());
                }catch (Exception e){
                    System.out.println("发生异常"+e.getMessage());
                }
            }
        },5,TimeUnit.SECONDS);

        System.out.println("go on ....");


        ByteBuf buf = (ByteBuf) msg;
        System.out.println("服务器读取线程："+Thread.currentThread().getName());
        System.out.println("客户端发送消息是："+buf.toString(CharsetUtil.UTF_8));
        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline();//是一个双向链表，出栈入栈
        System.out.println("客户端地址："+ channel.remoteAddress());
    }

    /**
     * 数据读取完毕
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        /**
         * 将数据写入到缓存，并刷新
         * 一般来讲，我们对这个发送的数据进行编码
         */
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端~",CharsetUtil.UTF_8));
    }

    /**
     * 处理异常，需要进行通道的关闭
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
