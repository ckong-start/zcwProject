package com.scw.webui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scw.webui.bean.TMember;

@Controller
@RequestMapping("/hello")
public class HelloController {

	@GetMapping("/test")
	public String testThymeleaf(Model model, HttpSession session) {
		//从域中获取数据
		model.addAttribute("reqKey", "reqVal");
		session.setAttribute("sessionKey", "sessionVal");
		//application域
		session.getServletContext().setAttribute("appKey", "appVal");
		//从域中获取引用数据类型
		model.addAttribute("member", new TMember(1, "zhangsan", "123456", null, "zs@qq.com"));
		session.setAttribute("user", new TMember(2, "lisi", "123456", null, "ls@qq.com"));
		
		//域中存入集合
		
		List<TMember> list = new ArrayList<TMember>();
		list.add(new TMember(1, "马呈祥", "123456", "anni", "a@1232.com"));
		list.add(new TMember(2, "李琛", "123456", "anni", "a@1232.com"));
		list.add(new TMember(3, "杨絮柳", "123456", "anni", null));
		list.add(new TMember(4, "康雷建", "123456", "anni", null));
		list.add(new TMember(5, "熊志超", "123456", "anni", "a@1232.com"));
		model.addAttribute("members", list);
		
		return "hello-thymeleaf";
	}
}
