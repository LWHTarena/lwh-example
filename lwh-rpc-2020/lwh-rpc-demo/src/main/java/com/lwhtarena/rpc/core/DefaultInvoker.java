package com.lwhtarena.rpc.core;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author liwh
 * @Title: DefaultInvoker
 * @Package com.lwhtarena.rpc.core
 * @Description: 默认的网络调用组件
 * @Version 1.0.0
 * @date 2020/9/19 22:25
 */
public class DefaultInvoker<T> implements Invoker<T> {

    private ChannelHandlerContext ctx;
    private String requestId;
    private String identify;
    private Class<T> returnType;

    private T result;

    DefaultInvoker(Class<T> returnType, ChannelHandlerContext ctx, String requestId, String identify) {
        this.returnType = returnType;
        this.ctx = ctx;
        this.requestId = requestId;
        this.identify = identify;
    }

    @SuppressWarnings("unckecked")
    @Override
    public T invoke(Object[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("interfaces", identify);
        JSONObject param = new JSONObject();
        if (args != null) {
            for (Object obj : args) {
                param.put(obj.getClass().getName(), obj);
            }
        }
        jsonObject.put("parameter", param);
        jsonObject.put("requestId", requestId);
        String msg = jsonObject.toJSONString() + "$$";
        ByteBuf byteBuf = Unpooled.buffer(msg.getBytes().length);
        byteBuf.writeBytes(msg.getBytes());
        ctx.writeAndFlush(byteBuf);
        waitForResult();
        return result;
    }

    @Override
    public void setResult(String result) {
        synchronized (this) {
            this.result = JSONObject.parseObject(result, returnType);
            notifyAll();
        }
    }


    private void waitForResult() {
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
