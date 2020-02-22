package com.atcrowdfunding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DispatcherController {
	//进入首页
	@GetMapping(value={"/", "/index", "/default", "/index.html"})
	public String toIndexPage() {
		return "index";
	}
	
	//进入登录页面
	@GetMapping(value="/login.html")
	public String toLogin() {
		return "admin/login";
	}
}
