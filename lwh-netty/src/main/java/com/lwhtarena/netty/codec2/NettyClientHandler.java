package com.lwhtarena.netty.codec2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Random;

/**
 * @author liwh
 * @Title: NettyClientHandler
 * @Package com.lwhtarena.netty.codec
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/14 16:25
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("服务器回复的消息:"+ buf.toString(CharsetUtil.UTF_8));
        System.out.println("服务器的地址："+ ctx.channel().remoteAddress());
    }

    /**当通道就绪就会触发该方法**/
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /**随机发送student或者worker对象**/
        int random =new Random().nextInt(3);
        MyDataInfo.MyMessage myMessage =null;
        if(0 == random){
            myMessage =MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.Student)
                    .setStudent(MyDataInfo.Student.newBuilder().setId(18).setName("玉麒麟 卢俊义").build()).build();
        }else{
            myMessage =MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.Worker)
                    .setWorker(MyDataInfo.Worker.newBuilder().setAge(26).setName("老王").build()).build();

        }
        ctx.writeAndFlush(myMessage);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
