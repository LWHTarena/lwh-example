package com.lwhtarena.netty.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author liwh
 * @Title: TestHeartbeatServer
 * @Package com.lwhtarena.netty.heartbeat
 * @Description: 测试心跳
 * @Version 1.0.0
 * @date 2020/6/13 19:57
 */
public class TestHeartbeatServer {
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
                            /**
                             * 加入netty提供的IdleStateHandler
                             * IdleStateHandler是netty提供的处理空闲状态的处理器
                             * 参数说明：
                             *  long readerIdleTime,表示多长时间没有读，就发送一个心跳检测包检测是否连接
                             *  long writerIdleTime,表示多长时间没有写，就发送一个心跳检测包检测是否连接
                             *  long allIdleTime，表示多长时间没有读写，就发送一个心跳检测包检测是否连接
                             *
                             * 当IdleStateEvent触发后，就会传递给管道的下一个handler去处理，
                             * 通过调用（触发）下一个handler的userEventTiggered，在该方法中去处理IdleStateEvent(读空闲，写空闲，读写空闲)
                             */
                            pipeline.addLast(new IdleStateHandler(7000,7000,10, TimeUnit.SECONDS));
                            /**加入一个空闲检测进一步处理的handler自定义**/
                            pipeline.addLast(new MyServerHandler());
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
