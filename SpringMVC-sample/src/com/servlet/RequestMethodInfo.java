package com.servlet;

import java.lang.reflect.Method;

public class RequestMethodInfo {
	//controller控制器中的方法
	private Method method;
	//目标对象controller控制器
	private Object controller;
	public RequestMethodInfo() {
		super();
	}
	public RequestMethodInfo(Method method, Object controller) {
		super();
		this.method = method;
		this.controller = controller;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public Object getController() {
		return controller;
	}
	public void setController(Object controller) {
		this.controller = controller;
	}
	/**
	 * 调用目标方法
	 */
	public Object invokeHandler(Object...args) {
		try {
			return method.invoke(controller, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
