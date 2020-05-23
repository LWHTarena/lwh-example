package com.lwhtarena.spring.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author liwh
 * @Title: UserListener
 * @Package com.lwhtarena.spring.servlet
 * @Description:监听项目的启动和停止
 * @Version 1.0.0
 * @date 2020/5/23 10:03
 */
public class UserListener implements ServletContextListener {


    //监听ServletContext销毁
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
        System.out.println("UserListener...contextDestroyed...");
    }

    //监听ServletContext启动初始化
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
        ServletContext servletContext = arg0.getServletContext();
        System.out.println("UserListener...contextInitialized...");
    }

}