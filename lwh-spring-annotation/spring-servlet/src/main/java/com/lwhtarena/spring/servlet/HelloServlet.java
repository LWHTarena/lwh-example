package com.lwhtarena.spring.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liwh
 * @Title: HelloServlet
 * @Package com.lwhtarena.spring.servlet
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/23 09:50
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //super.doGet(req, resp);
        System.out.println(Thread.currentThread()+" start...");
        try {
            sayHello();
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.getWriter().write("hello...");
        System.out.println(Thread.currentThread()+" end...");
    }

    public void sayHello() throws Exception{
        System.out.println(Thread.currentThread()+" processing...");
        Thread.sleep(3000);
    }

}
