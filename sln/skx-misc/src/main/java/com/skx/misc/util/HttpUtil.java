package com.skx.misc.util;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {

	public static String getIpAddrByRequest(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			//
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
				//
				if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
					ip = request.getHeader("HTTP_CLIENT_IP");
					//
					if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
						ip = request.getHeader("HTTP_X_FORWARDED_FOR");
						//
						if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
							ip = request.getRemoteAddr();
							//
							if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
								ip = "unknown";
							}
						}
					}
				}
			}
		}
		//
		return ip;
	}
}
