package com.lwhtarena.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

/**
 * @author liwh
 * @Title: MyTextWebsocketFrameHandler
 * @Package com.lwhtarena.netty.websocket
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/13 21:44
 */
public class MyTextWebsocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("服务器收到的消息："+ msg.text());
        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间:"+ LocalDateTime.now()+" "+ msg.text()));
    }

    /**
     * 当web客户端连接后，触发消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded 被调用："+ ctx.channel().id().asLongText());
        System.out.println("handlerAdded 被调用："+ ctx.channel().id().asShortText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded 被调用："+ ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常发生："+ cause.getMessage());
        ctx.close();
    }
}
