package com.lwhtarena.spring.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author liwh
 * @Title: MyApplicationListener
 * @Package com.lwhtarena.spring.ext
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/23 09:05
 */
@Component
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {
    //当容器中发布此事件以后，方法触发
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        // TODO Auto-generated method stub
        System.out.println("收到事件："+event);
    }
}
