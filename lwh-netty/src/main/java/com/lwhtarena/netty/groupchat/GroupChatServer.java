package com.lwhtarena.netty.groupchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author liwh
 * @Title: GroupChatServer
 * @Package com.lwhtarena.netty.groupchat
 * @Description: netty 模拟在线聊天（群）
 * @Version 1.0.0
 * @date 2020/6/13 08:17
 */
public class GroupChatServer {
    private int port;

    public GroupChatServer(int port) {
        this.port = port;
    }

    /**
     * 编写run方法，处理客户端的请求
     */
    public void run(){
        /**
         * 创建两个线程组
         */
        EventLoopGroup bossgroup = new NioEventLoopGroup();
        EventLoopGroup workergroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossgroup, workergroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            /**获取得到pipeline**/
                            ChannelPipeline pipeline = sc.pipeline();
                            /**向pipeline加入解码器**/
                            pipeline.addLast("decoder",new StringDecoder());
                            /**向pipeline加入编码器**/
                            pipeline.addLast("encoder",new StringEncoder());
                            /**加入自己的业务处理handler**/
                            pipeline.addLast("myhandler",new GroupChatServerHandler());
                        }
                    });
            System.out.println("netty 服务器启动~~");
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            /**监听关闭**/
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossgroup.shutdownGracefully();
            workergroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new GroupChatServer(7000).run();
    }
}
