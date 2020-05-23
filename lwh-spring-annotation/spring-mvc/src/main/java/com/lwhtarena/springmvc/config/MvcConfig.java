package com.lwhtarena.springmvc.config;

import com.lwhtarena.springmvc.controller.MyFirstInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author liwh
 * @Title: MvcConfig
 * @Package com.lwhtarena.springmvc.config
 * @Description: WebMvcConfigurerAdapter 在spring 5.0 废弃
 * 官方推荐WebMvcConfigurer,也可以继承直接继承 WebMvcConfigurationSupport
 *
 * 1、WebMvcConfigurer是一个接口，提供很多自定义的拦截器，例如跨域设置、类型转化器等等。
 * 可以说此接口为开发者提前想到了很多拦截层面的需求，方便开发者自由选择使用。由于
 * Spring5.0废弃了WebMvcConfigurerAdapter，所以WebMvcConfigurer继承了WebMvcConfigurerAdapter大部分内容。
 *
 * 2.configureContentNegotiation(ContentNegotiationConfigurer configurer)
 * 这个东西直译叫做内容协商机制，主要是方便一个请求路径返回多个数据格式。ContentNegotiationConfigurer这个配置里面你会看到MediaType，里面有众多的格式。此方法不在多赘述。
 *
 * 3.configureAsyncSupport(AsyncSupportConfigurer configurer)
 * 顾名思义，这是处理异步请求的。只能设置两个值，一个超时时间（毫秒，Tomcat下默认是10000毫秒，即10秒），还有一个是AsyncTaskExecutor，异步任务执行器。
 *
 * 4.configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
 * 这个接口可以实现静态文件可以像Servlet一样被访问。
 *
 * 5.addFormatters(FormatterRegistry registry)
 * 增加转化器或者格式化器。这边不仅可以把时间转化成你需要时区或者样式。还可以自定义转化器和你数据库做交互，比如传进来userId，经过转化可以拿到user对象。
 *
 * 6.addInterceptors(InterceptorRegistry registry)
 *
 * 7.addResourceHandlers(ResourceHandlerRegistry registry)
 * 自定义资源映射。这个东西也比较常用，业务场景就是自己的服务器作为文件服务器，不利用第三方的图床，就需要一个虚拟路径映射到我们服务器的地址。值得一提的是，如果你的项目是war包启动，一般都是再Tomcat中配置一下（配置方法请百度）；如果是jar包启动（SpringBoot经常用这种方式启动），就可以用到这个方法了。例如：
 *
 * 8.addCorsMappings(CorsRegistry registry)
 * 这个是设置跨域问题的，几乎是每个后台服务器都需要配置的东西。我曾写过一篇文章，专门讲跨域问题和SpringBoot怎么配置的，请查询：
 * https://juejin.im/post/5cfe6367f265da1b9163887f
 *
 * 9.addViewControllers(ViewControllerRegistry registry)
 * 这个方法可以实现，一个路径自动跳转到一个页面。不过现在多为前后端分离的项目，是不是可以把跳转路由的问题直接扔给前端。
 *
 * 后面还有七个：configureViewResolvers、addArgumentResolvers、addReturnValueHandlers、configureMessageConverters、extendMessageConverters、configureHandlerExceptionResolvers、extendHandlerExceptionResolvers。是在用的太少了，就不再看了。
 *
 * @Version 1.0.0
 *
 *  @date 2020/5/23 13:21
 */
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableScheduling
@ComponentScan(value="com.lwhtarena.springmvc",includeFilters={
        @ComponentScan.Filter(type= FilterType.ANNOTATION,classes={Controller.class})
},useDefaultFilters=false)
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * jsp视图解析器
     * @return
     */
    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(org.springframework.web.servlet.view.JstlView.class);
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".jsp");
        return bean;
    }

    /**
     *  公共部分解析器
     * @return
     */
    @Bean(name="multipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver(){
        CommonsMultipartResolver common = new CommonsMultipartResolver();
        common.setMaxUploadSize(10 * 1024 * 1024);//10M
        return common;
    }

    @Bean
    public MyFirstInterceptor getMyInterceptor(){
        return  new MyFirstInterceptor();
    }


    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public  void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(this.getMyInterceptor())
                .addPathPatterns("/**");
    }

}
