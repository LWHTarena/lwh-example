package com.lwhtarena.springmvc.config;

import com.lwhtarena.springmvc.controller.MyFirstInterceptor;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author liwh
 * @Title: AppConfig
 * @Package com.lwhtarena.springmvc.config
 * @Description:  WebMvcConfigurerAdapter 在spring 5.0 废弃
 * 官方推荐WebMvcConfigurer,也可以继承直接继承 WebMvcConfigurationSupport
 * @Version 1.0.0
 * @date 2020/5/23 13:07
 */
//SpringMVC只扫描Controller；子容器
//useDefaultFilters=false 禁用默认的过滤规则；

//@ComponentScan(value="com.lwhtarena.springmvc",includeFilters={
//        @ComponentScan.Filter(type= FilterType.ANNOTATION,classes={Controller.class})
//},useDefaultFilters=false)
//@EnableWebMvc
@Deprecated
public class AppConfig  extends WebMvcConfigurerAdapter {

    //定制

    //视图解析器
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // TODO Auto-generated method stub
        //默认所有的页面都从 /WEB-INF/ xxx .jsp
        //registry.jsp();
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    //静态资源访问
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // TODO Auto-generated method stub
        configurer.enable();
    }

    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // TODO Auto-generated method stub
        //super.addInterceptors(registry);
        registry.addInterceptor(new MyFirstInterceptor()).addPathPatterns("/**");
    }

}
