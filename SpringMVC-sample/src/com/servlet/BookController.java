package com.servlet;

public class BookController {
	@RequestMapping(value="/add")
	public String add() {
		System.out.println("add() is used");
		return "/ok.jsp";
	}
	@RequestMapping(value="/update")
	public String update() {
		System.out.println("update() is used");
		return "/ok.jsp";
	}
	
	public String query() {
		System.out.println("query() is used");
		return "/ok.jsp";
	}
}
