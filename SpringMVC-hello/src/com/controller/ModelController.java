package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ModelController {
	@RequestMapping("/modelAndView2Request")
	public ModelAndView modelAndView2Request() {
		System.out.println("modelAndView2Request is used");
		ModelAndView modelAndView = new ModelAndView("mapandmodelandmodelMap");
		modelAndView.addObject("modelAndViewKey1", "modelAndViewValue1");
		modelAndView.addObject("modelAndViewKey2", "modelAndViewValue2");
		
		return modelAndView;
	}
	
	@RequestMapping("/test")
	public String testException() {
		System.out.println("这是目标方法");
		int i = 12 / 0;
		return "redirect:/index.jsp";
	}
}
