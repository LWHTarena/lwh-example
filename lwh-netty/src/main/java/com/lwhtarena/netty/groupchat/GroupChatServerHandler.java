package com.lwhtarena.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liwh
 * @Title: GroupChatServerHandler
 * @Package com.lwhtarena.netty.groupchat
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/13 08:31
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channelGroup =new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSS'Z'");

    /**handlerAdded表示连接建立，一旦连接，**/
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "加入聊天" + sdf.format(new Date()) + "\n");
        channelGroup.add(channel);
    }

    /**断开连接，将xx客户离开信息推送给当前在线的客户**/
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]"+channel.remoteAddress()+"离开了\n");
        System.out.println("channelGroup size: "+channelGroup.size());
    }

    /**表示channel处于活动状态，提示xx上线**/
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"上线了~~");
    }

    /**表示channel处于不活动状态，提示xx离线**/
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"离线了~~");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        /**获取当前的channel**/
        Channel channel = ctx.channel();
        channelGroup.forEach(ch ->{
            if(channel !=ch){
                ch.writeAndFlush("[客户]"+channel.remoteAddress()+"发送了消息:"+msg+"\n");
            }else {
                ch.writeAndFlush("[自己]发送了消息: "+msg+"\n");
            }
        });
    }

    /**异常处理**/
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
