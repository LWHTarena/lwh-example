package com.lwhtarena.quartz.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;

public class WebInitializer implements WebApplicationInitializer {

    private static final Logger logger = LoggerFactory.getLogger(WebInitializer.class);

    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        logger.info("begin init web application.");

        //配置Spring
        AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();
        springContext.register(SpringConfig.class);

        //添加linstener
        servletContext.addListener(new ContextLoaderListener(springContext));

        //添加servlet
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
                "dispatcher", new DispatcherServlet(springContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        //添加filter
//        LoggerFilter loggerFilter = new LoggerFilter();
//        FilterRegistration.Dynamic logFilterRegistration=container.addFilter("requestResponseLogFilter", loggerFilter);
//        logFilterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC), false, "/*");

        logger.info("init web application success.");
    }
}
