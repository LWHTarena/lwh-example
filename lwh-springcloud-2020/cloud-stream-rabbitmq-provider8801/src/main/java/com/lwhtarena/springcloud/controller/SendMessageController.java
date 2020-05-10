package com.lwhtarena.springcloud.controller;

import com.lwhtarena.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liwh
 * @Title: SendMessageController
 * @Package com.lwhtarena.springcloud.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/10 21:57
 */
@RestController
public class SendMessageController {
    @Resource
    private IMessageProvider messageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage() {
        return messageProvider.send();
    }
}
