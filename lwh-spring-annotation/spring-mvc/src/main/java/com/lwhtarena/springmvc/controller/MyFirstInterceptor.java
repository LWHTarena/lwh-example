package com.lwhtarena.springmvc.controller;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liwh
 * @Title: MyFirstInterceptor
 * @Package com.lwhtarena.springmvc.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/23 13:13
 */
public class MyFirstInterceptor implements HandlerInterceptor {

    //目标方法运行之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // TODO Auto-generated method stub
        System.out.println("preHandle..."+request.getRequestURI());
        return true;
    }

    //目标方法执行正确以后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("postHandle...");

    }

    //页面响应以后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
        System.out.println("afterCompletion...");
    }

}
