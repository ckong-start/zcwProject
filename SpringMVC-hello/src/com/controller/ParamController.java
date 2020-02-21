package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ParamController {

	@RequestMapping("/getParam")
	public String getParam(@RequestParam(name="username",defaultValue="qwe1")String name) {
		System.out.println(name);
		return "ok";
	}
	
	@RequestMapping("/requestAPI")
	public String requestAPI(HttpServletRequest request) {
		String uri = request.getRequestURI();
		StringBuffer url = request.getRequestURL();
		System.out.println("uri = " + uri);
		System.out.println("url = " + url);
		
		return "ok";
	}
	
	@RequestMapping("/cookie")
	public String cookie(@CookieValue(name="JSESSIONID1",defaultValue="123")String cookie) {
		System.out.println(cookie);
		
		return "ok";
	}
}
