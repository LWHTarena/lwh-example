package com.lwhtarena.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @author liwh
 * @Title: TestHttpServerHandler
 * @Package com.lwhtarena.netty.http
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/12 06:38
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        System.out.println("对应的channel="+ctx.channel()+" pipeline="+ctx.pipeline()+" 通过pipeline获取channel："+ctx.pipeline().channel());
        System.out.println("当前的ctx的handler="+ctx.handler());

        if(msg instanceof HttpRequest){
            System.out.println("msg 类型:"+msg.getClass());
            System.out.println("客户端地址："+ctx.channel().remoteAddress());

            /**对特殊请求进行过滤**/
            HttpRequest httpRequest = (HttpRequest) msg;
            URI uri = new URI(httpRequest.uri());
            if("/favicon.ico".equals(uri.getPath())){
                System.out.println("请求了favicon.ico,不要响应");
                return;
            }

            /**回复信息给浏览器【http协议】**/
            ByteBuf content =Unpooled.copiedBuffer("hello,我是服务器", CharsetUtil.UTF_8);
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=UTF-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

            /**将构建好的response返回**/
            ctx.writeAndFlush(response);
        }
    }
}
