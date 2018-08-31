package com.lwhtarena.web;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * </p>
 *
 *  随着Servlet 3.0规范的发布，可以用几乎没有xml来配置你的Servlet容器。 为此，Servlet规范中有ServletContainerInitializer。
 *  在这个类中，你可以注册过滤器，监听器，servlet等，就像你在web.xml中一样。
 *
 *  Spring提供了知道如何处理WebApplicationInitializer类的SpringServletContainerInitializer。
 *  AbstractAnnotationConfigDispatcherServletInitializer类实现了内部实现WebApplicationInitializer的
 *  WebMvcConfigurer。 它注册一个ContextLoaderlistener（可选）和DispatcherServlet，并允许您轻松添加配置
 *  类来加载这两个类，并将过滤器应用于DispatcherServlet并提供servlet映射。
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { HibernateConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebMvcConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
