package com.lwhtarena.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * @author liwh
 * @Title: RestfulcurdApplication
 * @Package com.lwhtarena.crud
 * @Description: 主启动类
 * @Version 1.0.0
 * @date 2020/5/24 16:44
 */
@SpringBootApplication
public class RestfulcurdApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestfulcurdApplication.class,args);
    }

    @Bean
    public ViewResolver MyViewResolver(){
        return new MyViewResolver();
    }

    private class MyViewResolver implements ViewResolver {
        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            return null;
        }
    }
}
