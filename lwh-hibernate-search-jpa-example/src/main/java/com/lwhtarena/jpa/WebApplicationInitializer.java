package com.lwhtarena.jpa;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * </p>
 *
 * 在 Servlet 3.0 环境下，Servlet 容器会在 classpath 下搜索实现了 javax.servlet.ServletContainerInitializer
 * 接口的任何类，找到之后用它来初始化 Servlet 容器。
 *
 * Spring 实现了以上接口，实现类叫做 SpringServletContainerInitializer， 它会依次搜寻实现了 WebApplicationInitializer的
 * 任何类，并委派这个类实现配置。之后，Spring 3.2 开始引入一个简易的 WebApplicationInitializer 实现类，这就是
 * AbstractAnnotationConfigDispatcherServletInitializer。
 *
 * 所以 WebApplicationInitializer 继承 AbstractAnnotationConfigDispatcherServletInitializer之后，也就是间接实现了
 * WebApplicationInitializer，在 Servlet 3.0 容器中，它会被自动搜索到，被用来配置 servlet 上下文。
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 根容器
     * 获取配置信息
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { MvcConfig.class, PersistenceJPAConfig.class}; //CacheConfig.class
    }

    /**
     * Spring mvc容器
     *
     *
     * DispatcherServlet 开始启动时，会产生一个 Spring 应用程序上下文，把它和配置文件中声明的 bean
     * 或者类一起加载进来。通过getServletConfigClasses() 方法，设置 DispatcherServlet 通过 WebConfig
     * 配置类来完成 Spring 上下文和 bean 的加载。
     *
     * 但是在 Spring web 程序中，往往还有另外一个应用程序上下文，它是由 ContextLoaderListener 产生的。
     * 通过调用 getRootConfigClasses()方法返回的类就是用来配置 ContextLoaderListener 产生的上下文。
     *
     * 其中，DispatcherServlet 是用来加载涉及 web 功能的 beans，例如 controllers， view resolvers，
     * 和 handler mappings；而 ContextLoaderListener 则是用来载入程序中其余的 beans，例如一些中间层
     * 和数据层组件，完成的是程序后端功能。
     *
     *
     * AbstractAnnotationConfigDispatcherServletInitializer 产生了一个 DispatcherServlet 和一个 ContextLoaderListener
     *
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { MvcConfig.class,PersistenceJPAConfig.class };
    }

    /**
     * /DispatcherServlet映射,从"/"开始
     *
     * getServletMappings()，为 DispatcherServlet 提供一个或更多的Servlet 映射；这里是被映射到 /，
     * 指示它为默认的 servlet，用来操作所有来到程序的 Request。
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        return null;
    }
}
