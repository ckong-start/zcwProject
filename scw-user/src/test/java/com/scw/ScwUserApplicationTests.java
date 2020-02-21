package com.scw;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.scw.user.mapper.TMemberMapper;
import com.scw.user.utils.HttpUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScwUserApplicationTests {
	@Autowired
	TMemberMapper memeberMapper;

	@Test
	public void contextLoads() {
		long l = memeberMapper.countByExample(null);
		log.info("查询用户的个数为：{}", l);
		/*
		 * logger.debug("输出日志测试：debug"); logger.info("输出日志测试：info");
		 * logger.warn("输出日志测试：warn"); logger.error("输出日志测试：error");
		 */
	}

	@Test
	public void testSms() {
		boolean flag = false;
		String host = "http://dingxin.market.alicloudapi.com";
		String path = "/dx/sendSms";
		String method = "POST";
		String appcode = "3f02dea0631e453fa14a9bd9723c73d9";
		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("mobile", "18062978717");
		String code = UUID.randomUUID().toString().substring(0, 6);
		querys.put("param", "code:" + code);
		querys.put("tpl_id", "TP1711063");
		Map<String, String> bodys = new HashMap<String, String>();

		try {
			/**
			 * 重要提示如下: HttpUtils请从
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
			 * 下载
			 *
			 * 相应的依赖请参照
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
			 */
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
		log.error("返回的结果是：{}", flag ? "成功" : "失败");
	}

}
