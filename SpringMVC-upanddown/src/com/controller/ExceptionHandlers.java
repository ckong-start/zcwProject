package com.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * @ControllerAdvice 注解标注在类上。表示此类中的异常处理方法是全局的
 */
@ControllerAdvice
public class ExceptionHandlers {
	@ExceptionHandler
	public String exceptionHandler1(Exception e) {
		/**
		 * @ExceptionHandler注解如果标注在方法上，当controller控制器中产生了异常。 就会执行@ExceptionHandler标注的方法<br/>
		 * 异常参数。就是用来接收 抛出的异常信息的<br/>
		 */
		System.out.println("exceptionHandler1 Exception ===>>>" + e);
		// 这个方法的返回值。就是最终的跳转路径
		return "/error.jsp";
	}
	@ExceptionHandler
	public String exceptionHandler2(RuntimeException e) {
		System.out.println("exceptionHandler2 Exception ===>>>" + e);
		return "/error.jsp";
	}
	@ExceptionHandler
	public String exceptionHandler3(ArithmeticException e) {
		System.out.println("exceptionHandler3 Exception ===>>>" + e);
		return "/error.jsp";
	}
}
