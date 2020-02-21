package com.scw.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 获取格式化好的当前时间
 */
public class AppDateUtils {

	public static String getFormatTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
	public static String getFormatTime(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date());
	}
	
	public static String getFormatTime(String pattern, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
}
