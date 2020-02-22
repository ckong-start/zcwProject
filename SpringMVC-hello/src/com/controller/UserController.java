package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="/user")
@Controller
public class UserController {
	
	@RequestMapping(value="/save")
	public String save() {
		System.out.println("userController的save运行");
		return "ok";
	}
	
}
