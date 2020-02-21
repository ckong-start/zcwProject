package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReturnController {

	@RequestMapping("/return1")
	public String return1() {
		System.out.println("return1() is used");
		return "ok";
	}
	@RequestMapping("/return2")
	public String return2() {
		System.out.println("return2() is used");
		
		/*当显式的使用forward请求转发做为跳转的时候，返回的路径，不会和视图解析器前后缀做拼接操作。而是直接 请求转发跳转<br/>
		  显式的请求转发后面只能写上完整的路径。
		  下同*/
		return "forward:/jsp/ok.jsp";
	}
	@RequestMapping("/return3")
	public String return3() {
		System.out.println("return3() is used");
		return "redirect:/jsp/ok.jsp";
	}
	
	@RequestMapping("/return4")
	public ModelAndView return4() {
		System.out.println("return4() is used");
		ModelAndView modelAndView = new ModelAndView();
		// 设置视图名 ，只要是简单的设置视图名，就会经过视图解析器解析（前缀+视图名+后缀，得到跳转路径）
		// 默认使用请求转发做为跳转的方式
		modelAndView.setViewName("ok");
		//上面的和下面的方式相同
		//ModelAndView modelAndView = new ModelAndView("ok");
		return modelAndView;
	}
	@RequestMapping("/return5")
	public ModelAndView return5() {
		System.out.println("return5() is used");
		ModelAndView modelAndView = new ModelAndView();
		//如果要显示的使用请求转发做为跳转方式，可以在setViewName的时候，写是：forward:/path
		modelAndView.setViewName("forward:/jsp/ok.jsp");
		return modelAndView;
	}
	@RequestMapping("/return6")
	public ModelAndView return6() {
		System.out.println("return6() is used");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/jsp/ok.jsp");
		return modelAndView;
	}
}
