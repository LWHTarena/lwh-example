package com.lwhtarena.jd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author liwh
 * @Title: IndexController
 * @Package com.lwhtarena.jd.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/8/23 13:37
 */
@Controller
public class IndexController {

    @GetMapping({"/","/index"})
    public String index() {
        return "index";
    }
}
