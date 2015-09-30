package com.skx.misc.helper;


/**
 * Web环境辅助类
 * 
 * @date 2014-08-23 modified by koqiui - 提供了多系统自适应支持
 */
public class WebEnvHelper {
	// properties配置文件名称列表（多个可用逗号分隔）
	private static final String confPropertyFileNames = "conf/configuration.properties";
	private static final PropertyConfiguration propertyConfigurer;
	// 消息配置
	private static final String messageFileNames = "i18n/information_zh_CN.properties,i18n/validation_zh_CN.properties";
	private static final PropertyConfiguration messageConfigurer;
	//
	private static Environment _env;

	static {
		propertyConfigurer = new PropertyConfiguration(confPropertyFileNames, "UTF-8");

		messageConfigurer = new PropertyConfiguration(messageFileNames, "UTF-8");
		 	 
		if (NetUtil.hasHostIp(propertyConfigurer.get("yikuaixiu.com.ip"))) {
			System.out.println("正在product服务器上运行");
			_env = Environment.Product;
		} else if (NetUtil.hasHostIp(propertyConfigurer.get("test.yikuaixiu.com.ip"))) {
			System.out.println("正在test服务器上运行");
			_env = Environment.Test;

		} else if (NetUtil.hasHostIp(propertyConfigurer.get("visi.yikuaixiu.com.ip"))) {
			System.out.println("正在visi服务器上运行");
			_env = Environment.Visi;

		} else {
			System.out.println("正在dev服务器上运行");
			_env = Environment.Dev;
		}
		//
	}


	public static String getConfig(String key) {
		return propertyConfigurer.get(key);
	}

	// 判断是否在yikuaixiu.com上运行
	public static boolean isRunndingOnYkxServer() {
		return _env == Environment.Product || _env == Environment.Visi;
	}

	public static String getMessage(String msgKey) {
		return messageConfigurer.get(msgKey);
	}

	public static Environment getEnvironment() {
		return _env;
	}

	public enum Environment {
		Product, Test, Dev, Visi
	}
}
