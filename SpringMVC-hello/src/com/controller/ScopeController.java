package com.controller;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScopeController {
	@RequestMapping("/requestScope")
	public String requestScope(HttpServletRequest request) {
		System.out.println("requestScope is used");
		request.setAttribute("requestKey1", "requestV1");
		request.setAttribute("requestKey2", "requestV2");
		return "scope";
	}
	
	@RequestMapping("/sessionScope")
	public String sessionScope(HttpSession session) {
		System.out.println("sessionScope is used");
		session.setAttribute("sessionKey1", "sessionV1");
		session.setAttribute("sessionKey2", "sessionV2");
		return "scope";
	}
	
	@RequestMapping("/servletContext")
	public String servletContext(HttpSession session) {
		System.out.println("servletContext is used");
		ServletContext servletContext = session.getServletContext();
		servletContext.setAttribute("servletContextKey1", "servletContextV1");
		servletContext.setAttribute("servletContextKey2", "servletContextV2");
		return "scope";
	}
}
