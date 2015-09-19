package com.ykx.webapi.sms;

import ykx.tpgw.sms.ISmsService;
import ykx.tpgw.sms.SmsService;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;

//import com.ykx.common.util.JsonUtil;

/**
 * Created by vj on 2015/09/14.
 */

@WebServlet(name = "SmsServlet", urlPatterns = {"/webapi/sms"})
public class SmsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Log logger = LogFactory.getLog(SmsService.class);
    /*
   ?mobiles=[mobiles]&content=[content]&senderSystem=[senderSystem]&type=[type]
    */
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        send(req, res);
    }

    /*
    ?mobiles=[mobiles]&content=[content]&senderSystem=[senderSystem]&type=[type]
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        send(req, res);
    }

    protected void send(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        if (!authenticate(req, res)) {
            return;
        }

        String mobiles = req.getParameter("mobiles");
        String content = req.getParameter("content");
        String senderSystem = req.getParameter("senderSystem");
        String type = req.getParameter("type");

        res.setHeader("Content-type", "text/html;charset=UTF-8");
        Map<String, Object> responseResult = new HashMap<String, Object>();
        responseResult.put("result_ts", new Date());
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("mobiles", mobiles);
        data.put("content", content);
        responseResult.put("data", data);

        Gson gson=new Gson();
        //send sms
        ISmsService smsService = new SmsService();
        try {
            smsService.send(mobiles, content, senderSystem, type);
        } catch (Exception ex) {

            responseResult.put("result_code", "error");
            responseResult.put("result_msg", ex.toString());
            res.getWriter().println(gson.toJson(responseResult));
            return;
        }

        responseResult.put("result_code", "info");
        res.getWriter().println(gson.toJson(responseResult));
    }

    private boolean authenticate(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String clientIp = getRemoteIP(req);
        String clientIpWhiteList = "127.0.0.1,124.207.70.98";
        if (!clientIpWhiteList.contains(clientIp)) {
            res.sendError(401, "your ip is not involved in white list");
            return false;
        }
        return true;
    }

    private String getRemoteIP(HttpServletRequest request) {
        String fromSource = "X-Real-IP";
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
            fromSource = "X-Forwarded-For";
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            fromSource = "Proxy-Client-IP";
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            fromSource = "WL-Proxy-Client-IP";
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            fromSource = "request.getRemoteAddr";
        }
        logger.info("App Client IP: "+ip+", fromSource: "+fromSource);
        return ip;
    }
}

