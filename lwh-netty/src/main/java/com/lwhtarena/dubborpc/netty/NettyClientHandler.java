package com.lwhtarena.dubborpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * @author liwh
 * @Title: NettyClientHandler
 * @Package com.lwhtarena.dubborpc.netty
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/16 03:50
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext context; //上下文
    private String result; //返回结果
    private String para; //客户端调用方法时，传入的参数

    /**
     * 顺序 ---> 第3个被调用 --wait -> 然后第5个被调用
     * 被代理对象调用，发送数据给服务器， =》wait 等待被唤醒 =>返回结果
     * @return
     * @throws Exception
     */
    @Override
    public synchronized Object call() throws Exception {
        context.writeAndFlush(para);
        /**进行wait
         * 等待channelRead 方法获取到服务器的结果后，唤醒
         * **/
        wait();
        return result;
    }

    /**
     * 顺序 ---> 第1个被调用
     * 与服务器的连接创建后，就会被调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /**因为我们在其他方法会使用到ctx**/
        context =ctx;
    }

    /**
     * 顺序 ---> 第4个被调用
     * 收到服务器的数据后，
     *
     * notify() 唤醒等待的线程
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channel 被调用");
        result =msg.toString();

        /**唤醒等待的线程**/
        notify();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    /**
     * 顺序 ---> 第2个被调用
     * @param para
     */
    public void setPara(String para) {
        this.para = para;
    }
}
