package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="/book")
@Controller
public class BookController {
	
	@RequestMapping(value="/save")
	public String save() {
		System.out.println("bookController的save运行");
		return "ok";
	}
}
