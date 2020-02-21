package exer.util;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
public class LogUtils {
	
	@Pointcut("execution(public int exer.pojo.Calculator.*(..))")
	public static void pointcut1() {}
	
	@Before(value = "execution(public int exer.pojo.Calculator.add(int, int)) "
			+ "|| "
			+ "pointcut1()")
	public static void logBefore(JoinPoint jp) {
		System.out.println("前置操作 ，方法名是：" + jp.getSignature().getName() + 
				",运算数分别是：" + Arrays.asList(jp.getArgs()));
	}

	@After(value = "pointcut1()")
	public static void logAfter(JoinPoint jp) {
		System.out.println("后置操作 ，方法名是：" + jp.getSignature().getName() + 
				",运算数分别是：" + Arrays.asList(jp.getArgs()));
	}

	@AfterThrowing(value = "pointcut1()", throwing = "e")
	public static void logAfterThrowing(JoinPoint jp, Exception e) {
		System.out.println("异常操作 ，方法名是：" + jp.getSignature().getName() + 
				", 异常信息是：" + e);
	}

	@AfterReturning(value = "pointcut1()", returning = "result")
	public static void logAfterReturning(JoinPoint jp, Object result) {
		System.out.println("返回值操作 ，方法名是：" + jp.getSignature().getName() + 
				", 结果是：" + result);
	}

	@Around(value = "pointcut1()")
	public static Object around(ProceedingJoinPoint pjp) throws Throwable  {
		Object result = null;
		
		try {
			System.out.println("环绕前置通知~~" + pjp.getSignature().getName() + Arrays.asList(pjp.getArgs()));
			try {
				result = pjp.proceed();
			} finally {
				
				System.out.println("环绕后置通知~~");
			}
			System.out.println("环绕返回通知~~" + result);
		} catch (Throwable e) {
			System.out.println("环绕异常通知~~" + e);
			throw e;
		}
		return result;
	}	
	
}
