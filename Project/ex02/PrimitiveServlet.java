package com.tomcat.ex02;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/6/25.
 */
public class PrimitiveServlet implements Servlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("Init primitive servlet");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("from primitive servlet service");
        PrintWriter writer = res.getWriter();
        writer.println("Hello world.");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("Destory primitive servlet");
    }
}
