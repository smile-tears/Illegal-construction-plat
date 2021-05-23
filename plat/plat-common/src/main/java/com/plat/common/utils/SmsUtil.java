package com.plat.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class SmsUtil {

	public static String host = "https://jsdxqxt.js118114.com:9003/SMSController/sendSms.do";
	public static String auth_code = "2ad65e4f83b49b20a19bf6081c60603934cd97b7";
	public static String spid = "113899";
	public static int msgfmt = 8;
	public static String auth_key = "abeefb09d398833dba082804047d22bb";
	
	public static String sendSms(String smsid,String mobiles,String content) throws UnsupportedEncodingException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("auth_code", auth_code);
		map.put("spid", spid);
		map.put("smsid", smsid);
		map.put("mobiles", mobiles);
		String encContent = URLEncoder.encode(content, "utf-8");
		map.put("content", encContent);
		map.put("sendtime", "");
		String timestamp = String.valueOf(System.currentTimeMillis());
		map.put("timestamp", timestamp);
		map.put("msgfmt", String.valueOf(msgfmt));
		String sign = DigestUtils.sha1Hex(auth_code + spid + smsid + mobiles + encContent + timestamp + auth_key);
		map.put("sign", sign);
		System.out.println("===========短信发送请求参数："+JSON.toJSONString(map));
		String response = HttpUtil.sendPost(host, JSONObject.toJSONString(map), new HashMap<>());
		return response;
	}
	
//	public static void main(String[] args) {
//		try {
//			String result = sendSms(StringUtil.getUUID(), "15951880719", "【堰桥安监局】安全生产巡查记录限期整改，请登录系统查看！");
//			System.out.println(result);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
