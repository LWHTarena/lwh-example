package com.lwhtarena.spring.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liwh
 * @Title: UserServlet
 * @Package com.lwhtarena.spring.servlet
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/23 10:03
 */

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        resp.getWriter().write("tomcat...");
    }

}
