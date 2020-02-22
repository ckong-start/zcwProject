package com.servlet;

import java.util.HashMap;
import java.util.Map;

public class RequestPathAndRequestMethodMapping {
	/**
	 * key是请求地址
	 * value是controller控制器中的方法
	 */
	private Map<String, RequestMethodInfo> requestMapping = new HashMap<>();
	
	/**
	 * 保存请求地址和对应的controller控制器的信息
	 */
	public void add(String path, RequestMethodInfo requestMethodInfo) {
		requestMapping.put(path, requestMethodInfo);
	}
	
	/**
	 * 通过请求地址获取对应的controller控制器
	 */
	public RequestMethodInfo get(String path) {
		return requestMapping.get(path);
	}
}
