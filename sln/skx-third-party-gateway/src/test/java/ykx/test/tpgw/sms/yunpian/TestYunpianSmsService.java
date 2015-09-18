package ykx.test.tpgw.sms.yunpian;

import junit.framework.Assert;
import ykx.tpgw.sms.ISmsService;
import ykx.tpgw.sms.SmsService;

import org.junit.Test;

/**
 * Created by vj on 2015/09/11.
 */
public class TestYunpianSmsService {

    @Test
    public void testSendingSms(){
        ISmsService ss=new SmsService();
        try {
            ss.send("15050833162,1810131137","【易快修】您好#sir#您查询服务报价为#detail#","test","测试发送两条短信");
            ss.send("aa","ff","aa","b");
        }catch (Exception ex){
            Assert.fail(ex.getMessage());
        }

       // ss.send("aa","ff",new String[]{"aa","bb"});
    }

}
