package com.skx.demo.servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

    private static final Log logger = LogFactory.getLog(HelloWorldServlet.class);

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
            throws IOException {
        doAction(req, res, "get");
//        testLog4j();
        try {
            testTimeOut();
        }catch (Exception ex){
            res.getWriter().println(ex.toString());
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException{
        doAction(req, res, "post");
    }
    protected void doAction(HttpServletRequest req, HttpServletResponse res,String method)
            throws IOException{
        PrintWriter writer = res.getWriter();
        writer.println("Method: "+method);
        writer.println("in action");
    }

    private void testLog4j()
    {
        logger.debug("test debug");
        logger.info("test info");
        logger.warn("test warn");
        logger.error("test error");
        // Assert.assertTrue(false);
    }

    private void testTimeOut() throws InterruptedException
    {
        logger.info("testTimeOut start .....");
        Long sleep=10000L;
        Thread.sleep(sleep);
        logger.info("testTimeOut end .....");
        // Assert.assertTrue(false);
    }
}
