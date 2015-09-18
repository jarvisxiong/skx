package ykx.tpgw.sms;


import ykx.tpgw.sms.vendor.ISmsClient;
import ykx.tpgw.sms.vendor.SmsClientFactory;
import ykx.tpgw.sms.vendor.SmsSendingResult;

//import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.json.simple.JSONValue;
//import org.netsharp.communication.ServiceFactory;
//import org.netsharp.sms.ISmsLogService;
//import org.netsharp.sms.mandao.entity.SmsLog;
//
//import com.ykx.common.util.StrUtil;
//import com.ykx.common.web.WebEnvHelper;

/**
 * 对云片短信的封装
 * Created by vj on 2015/09/11.
 */
public class SmsService implements ISmsService {

    private static final Log logger = LogFactory.getLog(SmsService.class);
    private static ISmsClient client;

    public SmsService() {
        client = SmsClientFactory.createDefaultClient();
    }

    @Override
    public void send(String mobiles, String content, String appId, String type) throws Exception {
        if (!whetherSendingSms()) {
            return;
        }

        //todo 验证参数
        logger.debug(String.format("{starting_sending_sms:{'content':'%s', 'to': '%s'}}", content, mobiles));
        SmsSendingResult ssr = client.send(mobiles, content);

        if (ssr.getResult()) {
            logger.info(String.format("{finished_sending_sms:{'content':'%s', 'to': '%s'}}", content, mobiles));
            //log in db
            SaveSmsLog(mobiles, content, type, client.getSupplierName());
        } else {
            logger.info(String.format("{sending_sms:{'content':'%s', 'to': '%s','failed_reason':'%s'}", content, mobiles, ssr.getResultFromVendor()));
            throw new Exception(String.format("sending message failed, details: %s", ssr.getResultFromVendor()));
        }
    }

    @Override
    /**
     * @return 得到短信余额
     */
    public int getBalance() {
        try {
            String balance = client.getBalance();
            return Integer.valueOf(balance);
        } catch (Exception ex) {
            logger.error("can not check balance");
            return 0;
        }
    }

    private Boolean whetherSendingSms() {
//        return WebEnvHelper.getEnvironment() == WebEnvHelper.Environment.Product
//                || WebEnvHelper.getEnvironment() == WebEnvHelper.Environment.Test
//                || WebEnvHelper.getEnvironment() == WebEnvHelper.Environment.Visi
//                || WebEnvHelper.getEnvironment() == WebEnvHelper.Environment.Dev;
        return true;
    }

    private void SaveSmsLog(String mobile, String content, String type, String smsChannelSupplier) {
//        ISmsLogService logService = ServiceFactory.create(ISmsLogService.class);
//        SmsLog log = new SmsLog();
//        {
//            log.toNew();
//            log.setMobile(mobile);
//            log.setContent(content);
//            log.setType(type);
//            log.setSmsChannelSupplier(smsChannelSupplier);
//        }
//        logService.save(log);
    }
}
