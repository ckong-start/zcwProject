package com.springboot.config;


import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class AppConfig {
	//配置Druid的监控
	//1、配置一个管理后台的Servlet
	@Bean
	public ServletRegistrationBean statViewServlet() {
		ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		Map<String, String> initParams = new HashMap<>();
		initParams.put("loginUsername", "admin");
		initParams.put("loginPassword", "123456");
		initParams.put("allow", "");// 默认就是允许所有访问
		initParams.put("deny", "192.168.15.21");// 拒绝哪个ip访问
		bean.setInitParameters(initParams);
		return bean;
	}

	// 2、配置一个web监控的filter
	@Bean
	public FilterRegistrationBean webStatFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new WebStatFilter());
		Map<String, String> initParams = new HashMap<>();
		initParams.put("exclusions", "*.js,*.css,/druid/*");// 排除过滤
		bean.setInitParameters(initParams);
		bean.setUrlPatterns(Arrays.asList("/*"));
		return bean;
	}

	//只要返回值对象的类中，属性有get/set方法，指定前缀后，@ConfigurationProperties会自动根据前缀去配置文件中加载前缀对应的属性
	//再去类中查找对应属性的set方法，并将配置文件中的属性值传入到set方法中设置给对象
	@ConfigurationProperties(prefix="spring.datasource")
	@Bean
	public DataSource getDataSource() throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setFilters("stat"); 
		return dataSource;
	}
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/templates/");
		resolver.setSuffix(".html");
		return resolver;
	}
}
