package ykx.tpgw.sms.vendor;

import ykx.tpgw.sms.vendor.mandao.ManDaoClient;
import ykx.tpgw.sms.vendor.yunpian.YunPianClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import com.ykx.common.config.PropertyConfigurer;

/*
 * 短信发送工厂类
 * */
public class SmsClientFactory {

	private static SmsVendors defaultSmsVendor;
	private static final String confPropertyFileNames = "conf/configuration.properties";
	private static final Log logger = LogFactory.getLog(SmsClientFactory.class);

	static {
//		PropertyConfigurer propertyConfigurer = PropertyConfigurer.newInstance(
//				confPropertyFileNames, "UTF-8");
//		try {
//			defaultSmsVendor = SmsVendors.valueOf(
//					SmsVendors.class,
//					propertyConfigurer.get("defaultSmsVendor"));
//		} catch (Exception ex) {
//			logger.warn(ex.getMessage());
//			defaultSmsVendor = SmsVendors.yunpian;
//		}
		defaultSmsVendor = SmsVendors.yunpian;
	}

	public static ISmsClient create(SmsVendors smsChannelType) {

		ISmsClient smsClient = null;
		if (smsChannelType == SmsVendors.yunpian) {
			smsClient = new YunPianClient();
		} else if (smsChannelType == SmsVendors.mandao) {
			smsClient = new ManDaoClient();
		}
		return smsClient;
	}

	public static ISmsClient createDefaultClient() {
		return create(defaultSmsVendor);
	}
}