package com.ykx.webapi.sms;

import junit.framework.Assert;
import ykx.tpgw.sms.ISmsService;
import ykx.tpgw.sms.SmsService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.ykx.webapi.sms.sample.sample;

/**
 * Created by vj on 2015/09/14.
 */
public class TestSmsSending {

    String url="http://localhost/webapi/sms/sending";
    @Test
    public void testSendingSms(){
        Map<String,String> map=  new HashMap<String, String>();
        map.put("mobiles","15050833162");
        map.put("content","fff");
        map.put("senderSystem","Test");
        map.put("type","Test");

        sample.post(url,map);
    }

}
