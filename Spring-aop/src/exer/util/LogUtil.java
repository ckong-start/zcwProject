package exer.util;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogUtil {
	
	public static void logBefore(String methodName, Object...args) {
		System.out.println("前置操作 ，方法名是：" + methodName + ",运算数分别是：" + Arrays.asList(args));
	}
	public static void logAfter(String methodName, Object...args) {
		System.out.println("后置操作 ，方法名是：" + methodName + ",运算数分别是：" + Arrays.asList(args));
	}
	public static void logAfterThrowing(String methodName, Exception e) {
		System.out.println("异常操作 ，方法名是：" + methodName + ", 异常信息是：" + e);
	}
	public static void logAfterReturning(String methodName, Object result) {
		System.out.println("返回值操作 ，方法名是：" + methodName + ", 结果是：" + result);
	}

}
