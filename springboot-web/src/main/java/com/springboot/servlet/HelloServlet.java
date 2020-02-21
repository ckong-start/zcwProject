package com.springboot.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//1.实现特定接口Servlet（HttpServlet继承了GenericServlet，而GenericServlet实现了Servlet接口）
//2.将Servlet配置到web.xml中并提供映射地址，以下用注解替代
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("接受到了请求");
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().write("<span style='color:red;'>Hello!</span>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
