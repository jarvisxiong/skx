package com.skx.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by vj on 2015/08/29.
 */
public class HelloWorldServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public void service(HttpServletRequest req, HttpServletResponse res)
    throws IOException,ServletException{
        res.getWriter().println("service");
        super.service(req, res);
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse res)
            throws IOException{
        doAction(req, res, "delete");

    }

    protected void doPut(HttpServletRequest req, HttpServletResponse res)
            throws IOException{
        doAction(req, res, "put");

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException{
        doAction(req, res, "get");

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException{
        doAction(req,res,"post");
    }
    protected void doAction(HttpServletRequest req, HttpServletResponse res,String method)
            throws IOException{
        PrintWriter writer = res.getWriter();
        writer.println("Method: "+method);
        writer.println("in action");
    }
}
