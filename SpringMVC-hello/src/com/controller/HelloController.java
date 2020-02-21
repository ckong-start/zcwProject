package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Controller 作用相当于:<bean id="helloController" class="com.atguigu.controller.HelloController"/>
 */
@Controller
public class HelloController {
	/**
	 * @RequestMapping注解，可以配置一个请求地址对应 当前 方法<br/>
	 *  / 在web中解析表示地址为http://ip:port/工程名/<br/>
	 * 	/hello ==>> 地址为： http://ip:port/工程名/hello
	 */
	@RequestMapping(value="/hello", params="username!=123")
	public String hello() {
		System.out.println("hello方法调用了");
		return "ok";
	}
	@RequestMapping(value="/hello1", params="!username")
	public String hello1() {
		System.out.println("hello1方法调用了");
		return "ok";
	}
	//params="username!=123"    表示   1、请求参数中不能有username参数。2、有username参数，但值不能等于123
	@RequestMapping(value="/hello2", params= {"username!=123", "password"})
	public String hello2() {
		System.out.println("hello2方法调用了");
		return "ok";
	}
	@RequestMapping(value="/hello3", headers="User-Agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.79 Safari/537.36")
	public String hello3() {
		System.out.println("hello3方法调用了");
		return "ok";
	}

}
