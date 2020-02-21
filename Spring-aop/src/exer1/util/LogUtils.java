package exer1.util;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;

public class LogUtils {

	public static void logBefore(JoinPoint jp) {
		System.out.println("xml的前置操作，方法名是：" + jp.getSignature().getName() + "，方法的参数是：" + Arrays.asList(jp.getArgs()));
	}
	public static void logAfter(JoinPoint jp) {
		System.out.println("xml的后置操作，方法名是：" + jp.getSignature().getName() + "，方法的参数是：" + Arrays.asList(jp.getArgs()));
	}
	public static void logAfterReturning(JoinPoint jp, Object result) {
		System.out.println("xml的额返回值操作，方法名是：" + jp.getSignature().getName() + "，返回值是：" + result);
	}
	public static void logAfterThrowing(JoinPoint jp, Exception e) {
		System.out.println("xml的异常操作，方法名是：" + jp.getSignature().getName() + "，异常是：" + e);
	}
}
