package com.lwhtarena.spring.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author liwh
 * @Title: UserFilter
 * @Package com.lwhtarena.spring.servlet
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/23 10:01
 */

public class UserFilter implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {
        // 过滤请求
        System.out.println("UserFilter...doFilter...");
        //放行
        arg2.doFilter(arg0, arg1);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
