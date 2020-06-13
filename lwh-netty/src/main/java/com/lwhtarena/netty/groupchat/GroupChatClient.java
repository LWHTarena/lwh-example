package com.lwhtarena.netty.groupchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @author liwh
 * @Title: GroupChatClient
 * @Package com.lwhtarena.netty.groupchat
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/13 08:33
 */
public class GroupChatClient {
    private String host;
    private final int port;

    public GroupChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public void run(){
        EventLoopGroup group =new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            ChannelPipeline pipeline = sc.pipeline();
                            /**向pipeline加入解码器**/
                            pipeline.addLast("decoder",new StringDecoder());
                            /**向pipeline加入编码器**/
                            pipeline.addLast("encoder",new StringEncoder());
                            /**自定义handler业务**/
                            pipeline.addLast("myhandler",new GroupChatClientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            /**得到channel**/
            Channel channel =channelFuture.channel();
            System.out.println("~~~~~~~~"+channel.localAddress()+"~~~~~~~~");
            /**客户端需要输入信息，创建一个扫描器**/
            Scanner scanner =new Scanner(System.in);
            while (scanner.hasNextLine()){
                String msg =scanner.nextLine();
                channel.writeAndFlush(msg+"\r\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new GroupChatClient("127.0.0.1",7000).run();
    }
}
