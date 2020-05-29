package com.lwhtarena.crud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author liwh
 * @Title: MyMvcConfig
 * @Package com.lwhtarena.crud.config
 * @Description: WebMvcConfigurer可以来扩展SpringMVC的功能
 *   @EnableWebMvc   不要接管SpringMVC
 * @Version 1.0.0
 * @date 2020/5/24 22:50
 */
@Configuration
//@EnableWebMvc
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // super.addViewControllers(registry);
        //浏览器发送 /atguigu 请求来到 success
        registry.addViewController("/lwhtarena").setViewName("success");
    }
}
