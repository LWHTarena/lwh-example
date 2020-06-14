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
 * 集群之间的主节点与从节点之间实现数据的最终一致性。节点与节点之间实现数据的异步同步。节点与节点之间怎样
 * 才能感知对应节点状态。这就要求节点每隔一段时间定时的发送心跳包去感知对方的服务健康状态。一般在设置几个
 * 心跳包之后我们就可以认为对方节点已经挂了，我们就可以将该节点从集群中踢出去。
 *
 * 我们有个疑问，比如说之前的多客户端通信demo，当客户端断开与服务器连接的时候会触发handlerRemoved方法，
 * 那么我们就知道该服务的状态了。为什么还需要心跳包去感知呢？
 *
 * 真实情况远比我们想象中的复杂，比如我们的客户端是移动手机并且已经建立好了连接，当打开飞行模式（或者强制关机）
 * 的时候我们就无法感知当前连接已经断开了（handlerRemoved不会触发的），
 *
 * 当我们客户端和服务器端进行通信的时候，关闭网络或者打开飞行模式，此时通过handlerAdded方法和handlerRemoved
 * 是无法判断服务是否已经宕掉的。那么就引出了本文的内容。
 *
 * 什么是心跳检测？
 * 判断对方（设备，进程或其它网元）是否正常动行，一般采用定时发送简单的通讯包，如果在指定时间段内未收到对方响应，
 * 则判断对方已经宕掉。用于检测TCP的异常断开。
 *
 * 基本原因是服务器端不能有效的判断客户端是否在线，也就是说服务器无法区分客户端是长时间在空闲，还是已经掉线的情况。
 * 所谓的心跳包就是客户端定时发送简单的信息给服务器端告诉它我还在而已。
 *
 * 代码就是每隔几分钟发送一个固定信息给服务端，服务端收到后回复一个固定信息。如果服务端几分钟内没有收到客户端信息则
 * 视客户端断开。比如有些通信软件长时间不使用，要想知道它的状态是在线还是离线就需要心跳包，定时发包收包。
 *
 * 发包方可以是客户也可以是服务端，看哪边实现方便合理。一般是客户端。服务器也可以定时轮询发心跳下去。
 *
 * 一般来说，出于效率的考虑，是由客户端主动向服务器端发包，而不是相反。
 * 在分布式集群部署环境中也经常使用到心跳检测，比如主从服务之间的心跳检查，各master之间的互相检测等等，所以还是非
 * 常有实践意义的。
 *
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
