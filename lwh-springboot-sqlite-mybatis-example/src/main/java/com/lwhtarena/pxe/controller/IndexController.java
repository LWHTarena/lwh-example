package com.lwhtarena.pxe.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/")
public class IndexController {
    /**
     * 默认重定向到到index.html
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView index(){
        return new ModelAndView("redirect:index.html");
    }
}
