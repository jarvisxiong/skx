package ykx.tpgw.sms.vendor;

public interface ISmsClient {

	String getSupplierName();
	/**
	 * 发送短信
	 * @param mobiles 手机号码
	 * @param content 短信内容
	 */
	SmsSendingResult send(String mobiles, String content);
	
	/**
	 * 查询短信余额
	 */
	String getBalance();
}
