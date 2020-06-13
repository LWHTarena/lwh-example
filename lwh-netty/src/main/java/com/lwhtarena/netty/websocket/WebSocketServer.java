package com.lwhtarena.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author liwh
 * @Title: WebSocketServer
 * @Package com.lwhtarena.netty.websocket
 * @Description: WebSocket长连接
 * @Version 1.0.0
 * @date 2020/6/13 20:33
 */
public class WebSocketServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup =new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            ChannelPipeline pipeline = sc.pipeline();
                            /**基于http协议，使用http的编码和解码器**/
                            pipeline.addLast(new HttpServerCodec());
                            /**以块方式写，添加ChunkedWriteHandler处理器**/
                            pipeline.addLast(new ChunkedWriteHandler());
                            /**说明
                             * 1、因为http数据在传输过程中是分段的，HttpObjectAggregator就是可以将多个段聚合
                             * 2、这就是为什么，当浏览器发送大量数据时，就会发出多次http请求
                             * **/
                            pipeline.addLast(new HttpObjectAggregator(8192));//默认是65536 设置块的最大字节数10485760
                            /**
                             * 说明：
                             * 1、对应websocket，它的数据是以帧(frame)形式传递
                             * 2、是可以看到websocketframe下面有六个实现子类
                             * 3、浏览器请求时，ws://localhost:7000/xxx 表示请求的uri
                             * 4、WebSocketServerProtocolHandler 核心功能是将http协议升级为ws协议，保持长连接
                             */
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));

                            /**自定义业务handler**/
                            pipeline.addLast(new MyTextWebsocketFrameHandler());
                        }
                    });

            /**启动服务器**/
            ChannelFuture channelFuture = serverBootstrap.bind(7000).sync();
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
