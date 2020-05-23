package com.lwhtarena.springmvc.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * @author liwh
 * @Title: WebApplicationInitializer
 * @Package com.lwhtarena.springmvc.config
 * @Description: WebMvcConfigurerAdapter 在spring 5.0 废弃,官方推荐WebMvcConfigurer,也可以继承直接继承 WebMvcConfigurationSupport
 *
 * 功能描述：这是整个项目的核心。Servlet3.0规范，支持将web.xml相关配置也硬编
 *  * 码到代码中[servlet，filter，listener,等等]，并由javax.servlet.ServletContainerInitializer的
 *  * 实现类负责在容器启动时进行加载</h2>
 *  * <ol></ol>
 *  * </p>
 *  *
 *  * spring提供了一个实现类SpringServletContainerInitializer（在spring-web包中的org.springframework.web目录）,
 *  * 该类会调用所有org.springframework.web.WebApplicationInitializer实现类的onStartup方法，将相关的组件注册到服务
 *  * 器；而我们的WebApplicationInitializer继承自AbstractAnnotationConfigDispatcherServletInitializer，而
 *  * AbstractAnnotationConfigDispatcherServletInitializer就实现了org.springframework.web.WebApplicationInitializer的onStartup方法，
 *  * 所以WebApplicationInitializer就是整个项目的关键，我们的整个项目就是通过它来启动。
 * @Version 1.0.0
 * @date 2020/5/23 13:15
 */
public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 获取配置信息
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {DatabaseConfig.class};
    }
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { MvcConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        return null;
    }

}
