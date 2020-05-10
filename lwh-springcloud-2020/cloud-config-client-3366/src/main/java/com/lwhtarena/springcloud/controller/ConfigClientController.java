package com.lwhtarena.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liwh
 * @Title: ConfigClientController
 * @Package com.lwhtarena.springcloud.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/10 16:38
 */
@RestController
@RefreshScope
public class ConfigClientController {
    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String configInfo() {
        return "serverPort: "+serverPort+"\t\n\n configInfo: "+configInfo;
    }
}
