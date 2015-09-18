package ykx.tpgw.sms;

/**
 * 短信发送服务接口
 * Created by vj on 2015/09/11.
 */
public interface ISmsService {
    /**
     * 发送短信
     *
     * @param mobiles
     *            手机号码,用','隔开，支持10000个号码
     * @param content
     *            发送内容
     * @param appId
     *            触发短信发送的系统
     * @param type
     *              备注
     * @return 短信发送批次 @
     */
    void send(String mobiles, String content, String appId, String type)throws Exception;


    /**
     * @return 得到短信余额(短信条数)
     */
    int getBalance();
}
