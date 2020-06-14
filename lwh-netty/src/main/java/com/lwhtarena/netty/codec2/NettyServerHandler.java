package com.lwhtarena.netty.codec2;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author liwh
 * @Title: NettyServerHandler
 * @Package com.lwhtarena.netty.codec
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/14 16:11
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MyDataInfo.MyMessage msg) throws Exception {
        /**根据dataType来显示不同的信息**/
        MyDataInfo.MyMessage.DataType dataType =msg.getDataType();
        if(dataType == MyDataInfo.MyMessage.DataType.Student){
            MyDataInfo.Student student =msg.getStudent();
            System.out.println("学生id="+student.getId()+" 学生名字="+student.getName());
        }else if(dataType == MyDataInfo.MyMessage.DataType.Worker){
            MyDataInfo.Worker worker =msg.getWorker();
            System.out.println("工人名字="+worker.getName()+" 工人年龄="+worker.getAge());
        }else{
            System.out.println("传输类型不正确");
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端~~~哈哈", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
