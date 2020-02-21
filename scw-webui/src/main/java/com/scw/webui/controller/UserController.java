package com.scw.webui.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scw.common.bean.AppResponse;
import com.scw.webui.service.UserServiceFeignClient;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
	@Autowired
	UserServiceFeignClient userServiceFeignClient;

	//1.处理用户登录的方法
	@PostMapping("/doLogin")
	public String doLogin(String loginacct, String userpswd, Model model, HttpSession session) {
		log.debug("账号是：{}，密码是：{}", loginacct, userpswd);
		AppResponse<Object> response = userServiceFeignClient.doLogin(loginacct, userpswd);
		if (!"00000".equals(response.getCode())) {
			//登录失败
			log.debug("登录失败的错误信息：{}", response.getMessage());
			model.addAttribute("loginacct", loginacct);
			model.addAttribute("errorMsg", response.getMessage());
			return "user/login";
		}
		//如果是从paystep1页面跳转过来的，登录成功需要跳转回登录之前的页面
		String ref = (String) session.getAttribute("ref");
		if (!StringUtils.isEmpty(ref)) {
			//跳转回之前的页面
			session.setAttribute("user", response.getData());
			session.removeAttribute(ref);
			return "redirect:" + ref;
		}
		
		//登录成功将用户信息存入session域中
		session.setAttribute("user", response.getData());
		log.debug("登录的用户信息：{}", response.getData());
		return "redirect:/index";
	}
	
	//2.处理用户注销
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		userServiceFeignClient.logout(session);
		return "redirect:/index";
	}
	
	//3.处理用户发送短信功能
	@ResponseBody
	@PostMapping("/sendsms")
	public String sendsms(String phonenum) {
		log.info("收到的手机号码{}", phonenum);
		AppResponse<Object> response = userServiceFeignClient.sendSms(phonenum);
		if ("00000".equals(response.getCode())) {
			return (String) response.getData();
			
		}else {
			return response.getMessage();
		}
	}
}
