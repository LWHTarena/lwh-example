package com.lwhtarena.web.Filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * @author liwh
 * @Title: TimeFilter
 * @Package com.lwhtarena.web.Filter
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 22:37
 */
//@Component
public class TimeFilter implements Filter {

    /* (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        System.out.println("time filter destroy");
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("time filter start");
        long start = new Date().getTime();
        chain.doFilter(request, response);
        System.out.println("time filter 耗时:"+ (new Date().getTime() - start));
        System.out.println("time filter finish");
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig arg0) throws ServletException {
        System.out.println("time filter init");
    }

}
