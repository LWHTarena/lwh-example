package com.lwhtarena.spring.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author liwh
 * @Title: UserService
 * @Package com.lwhtarena.spring.ext
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/23 09:12
 */
@Service
public class UserService {

    @EventListener(classes={ApplicationEvent.class})
    public void listen(ApplicationEvent event){
        System.out.println("UserService。。监听到的事件："+event);
    }

}
