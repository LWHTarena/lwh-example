package com.lwhtarena.netty.groupchat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author liwh
 * @Title: GroupChatClientHandler
 * @Package com.lwhtarena.netty.groupchat
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/13 08:39
 */
public class GroupChatClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg.trim());
    }
}
