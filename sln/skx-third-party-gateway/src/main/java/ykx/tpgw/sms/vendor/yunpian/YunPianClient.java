package ykx.tpgw.sms.vendor.yunpian;

import ykx.tpgw.sms.vendor.SmsSendingResult;
import ykx.tpgw.sms.vendor.ISmsClient;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class YunPianClient implements ISmsClient {
    private static final Log logger = LogFactory.getLog(YunPianClient.class);

    private        String apikey    = "6f7a78b679fc9d9256bcdb";
    private static String signature = "【易快修】";

    public String getSupplierName()
    {
        return "YunPian";
    }
    /**
     * 发送短信
     *
     * @param mobile  手机号码
     * @param content 短信内容
     */
    public SmsSendingResult send(String mobile, String content) {
        SmsSendingResult sr = new SmsSendingResult();
        sr.setContent(content);
        sr.setPhone(mobile);
        try {
            if (!content.startsWith(signature)) {
                content = signature + content;
            }
            String r = YunPianAPI.sendSms(apikey, content, mobile);
            sr.setResultFromVendor(r);
            sr.setResult(r.contains("OK"));
            return sr;
        } catch (IOException e) {
            logger.error("短信发送失败：", e);
            sr.setResult(false);
            return sr;
        }
    }

    /**
     * 查询短信余额
     */
    public String getBalance() {
        return null;
    }
}
