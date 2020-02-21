package com.springboot.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
@WebListener
public class HelloListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("全局上下文对象初始化成功。。。");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("全局上下文对象即将销毁。。。");
	}
}
