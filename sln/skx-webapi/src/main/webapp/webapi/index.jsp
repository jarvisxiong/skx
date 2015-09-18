<%--
  Created by IntelliJ IDEA.
  User: vj
  Date: 2015/09/14
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
  <br/>发送短信：
  <br/>http://host:port/webapi/sms?mobiles=[mobiles]&content=[content]&appid=[appid]&type=[type]
  <br/>方法：POST
  <br/>参数：
  <br/>1.mobiles - 电话号码，多个号码用','分开
  <br/>2.content - 短信内容
  <br/>3.senderSystem - 短信发送系统，既调用此接口的系统
  <br/>4.type - 短信类别,如：
  <br/>返回值：
  <br/><p>{
          result_code:"error", //error,warn,info ...
          result_msg:"",
          result_ts:"", //timestamp
          result_data:{
          }
        }
        </p>
  </body>
</html>
