package com.scw.webui.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class AppWebMvcConfig implements WebMvcConfigurer {
	//如果controller的处理请求的方法没有业务代码可以将方法省略，在当前类中编写映射和页面的对应关系
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login.html").setViewName("user/login");
		registry.addViewController("/regist.html").setViewName("user/regist");
	}
}
