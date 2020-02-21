package com.scw.user.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class SmsTemplate {

	/**
	 * 1、提取方法中的 短信api的参数到properties配置文件中 将属性提取为成员变量 2、将由用户提交的请求参数集合 提取为方法的形参
	 * 3、由于项目的多个地方需要使用当前类的对象，可以将对象交给Spring容器管理
	 */
	private String host;
	private String path;
	private String method;
	private String appcode;

	public boolean sendMessage(Map<String, String> querys) {
		boolean flag = false;
		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> bodys = new HashMap<String, String>();
		try {
			HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
			String bodyStr = EntityUtils.toString(response.getEntity());
			log.warn("发送短信的响应体内容：{}", bodyStr);
			// 解析bodyStrjson字符串，判断短信发送成功与否
			Gson gson = new Gson();
			Map map = gson.fromJson(bodyStr, Map.class);
			log.info("解析响应体json字符串的结果为：{}", map);
			flag = "00000".equals(map.get("return_code"));
			// 获取response的body
			// System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//true代表短信发送成功
		return flag;
	}
}
