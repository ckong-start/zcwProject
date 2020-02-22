package com.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestPathAndRequestMethodMapping requestPathAndRequestMethodMapping = new RequestPathAndRequestMethodMapping();
	
	@Override
	public void init() throws ServletException {
		super.init();
		String projectPath = this.getServletContext().getContextPath();
		String className = "com.servlet.BookController";
		Object controller = null;
		try {
			Class clazz = Class.forName(className);
			controller = clazz.newInstance();
			Method[] declaredMethods = clazz.getDeclaredMethods();
			if (declaredMethods != null && declaredMethods.length > 0) {
				for (Method method : declaredMethods) {
					RequestMapping annotation = method.getAnnotation(RequestMapping.class);
					System.out.println(method.getName() + "-->" + annotation);
					if (annotation != null) {
						String path = annotation.value();
						System.out.println(path);
						String realPath = projectPath + path;
						requestPathAndRequestMethodMapping.add(realPath, new RequestMethodInfo(method, controller));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		RequestMethodInfo requestMethodInfo = requestPathAndRequestMethodMapping.get(requestURI);
		if (requestMethodInfo != null) {
			String forwardPath = (String) requestMethodInfo.invokeHandler(null);
			System.out.println(forwardPath);
			request.getRequestDispatcher(forwardPath).forward(request, response);
		}else {
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().write("请求的页面未找到~~");
		}
	}

}
