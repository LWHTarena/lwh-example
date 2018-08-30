package com.lwhtarena.search;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.*;

/**
 * <p>
 * <h2>简述：WebInitializer 取代web.xml</h2>
 * <ol></ol>
 * <h2>功能描述：无web.xml配置</h2>
 * <ol></ol>
 * </p>
 * 去除web.xml文件，用Java代码代替它。
 * Spring MVC提供了一个接口WebApplicationInitializer，用于替代web.xml配置文件。实现该接
 * 口的类会在Servlet容器启动时自动加载并运行。
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class WebInitializer implements WebApplicationInitializer {


    /**
     * Servlet容器启动时会自动运行该方法
     * @param servletContext
     * @throws ServletException
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx =new AnnotationConfigWebApplicationContext();
        ctx.register(MvcConfig.class);
        ctx.setServletContext(servletContext);

        Dynamic servlet =servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }
}
