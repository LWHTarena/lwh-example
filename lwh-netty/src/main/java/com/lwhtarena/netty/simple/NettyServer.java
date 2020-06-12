package com.lwhtarena.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liwh
 * @Title: NettyServer
 * @Package com.lwhtarena.netty.simple
 * @Description:
 *
 * NioServerSocketChannel 、NioSocketChannel使用一个静态的成员变量来存放provider的原因
 *
 * @Version 1.0.0
 * @date 2020/6/10 11:39
 */
@Slf4j
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        /**
         * 创建两个线程组bossgroup和workergroup
         * bossGroup 只是处理连接请求，真正的和客户端业务处理，会交给workerGroup完成
         * 两个线程组都是无线循环
         */
        EventLoopGroup bossGroup =new NioEventLoopGroup();
        NioEventLoopGroup workerGroup =new NioEventLoopGroup();


        try {
            /**
             * 创建服务端的启动对象，配置参数
             */
            ServerBootstrap serverBootstrap =new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup) //设置两个线程组
                    .channel(NioServerSocketChannel.class) //使用NioSocketChannel作为服务器的通道实现
                    .option(ChannelOption.SO_BACKLOG,128) //设置线程队列得到连接数
                    .childOption(ChannelOption.SO_KEEPALIVE,true) //设置保持活动链接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        /**
                         * 创建一个通道测试对应（匿名对象）
                         */
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            sc.pipeline().addLast(new NettyServerHandler());
                        }
                    }); //给我们的workerGroup的eventgroup对应的管道设置处理器

            log.info("...服务器启动...");

            /**
             * 绑定一个端口并且同步，生成一个ChannelFuture对象
             * 启动并绑定
             */
            ChannelFuture cf =serverBootstrap.bind(6668).sync();

            /**对关闭通道进行监听**/
            cf.channel().closeFuture().sync();
        }catch (Exception e){
            log.error("异常捕获:{}",e);
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
