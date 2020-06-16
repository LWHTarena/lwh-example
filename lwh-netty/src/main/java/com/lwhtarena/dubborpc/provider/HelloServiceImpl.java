package com.lwhtarena.dubborpc.provider;

import com.lwhtarena.dubborpc.publicInterface.HelloService;

/**
 * @author liwh
 * @Title: HelloServiceImpl
 * @Package com.lwhtarena.dubborpc.provider
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/16 03:21
 */
public class HelloServiceImpl implements HelloService {

    private static int count =0;

    /**
     * 当有消费方调用方法时，就返回一个结果
     * @param msg
     * @return
     */
    @Override
    public String hello(String msg) {
        System.out.println("收到客户端消息="+msg);
        if(msg !=null){
            return "您好，客户端，我已经收到你的消息["+msg+"] 第"+(++count)+"次";
        }else{
            return "您好，客户端，我已经收到你的消息";
        }
    }
}
