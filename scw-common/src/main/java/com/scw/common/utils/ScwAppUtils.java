package com.scw.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

public class ScwAppUtils {
	// 正则验证手机号码格式的方法
	public static boolean isMobilePhone(String phone) {
		boolean flag = true;
		String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
		if (phone.length() != 11) {
			flag = false;
		} else {
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(phone);
			flag = m.matches();
		}

		return flag;
	}
	
	// 保存对象到redis
	public static <T> void saveObj2Redis(T t, String token, StringRedisTemplate srt) {
		srt.opsForValue().set(token, JSON.toJSONString(t));
	}
	
	// 从redis获取jsonStr转化为对象
	public static <T> T jsonStr2Obj(Class<T> type, String token, StringRedisTemplate srt) {
		String jsonStr = srt.opsForValue().get(token);
		if (StringUtils.isEmpty(jsonStr)) {
			return null;
		}
		return JSON.parseObject(jsonStr, type);
	}
	
}
