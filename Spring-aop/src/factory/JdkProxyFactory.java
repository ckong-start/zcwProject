package factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import exer.pojo.Calculate;
import exer.pojo.Calculator;
import exer.util.LogUtil;
import sun.security.krb5.internal.MethodData;

public class JdkProxyFactory {
	/**
	 * 创建jdk代理对象<br/>
	 * target是被代理的对象
	 */
	public static Object createJdkProxy(Object target) {
		/**
		 * Proxy是反射包下的一个工具类。它可以帮我们创建Jdk动态代理对象<br/>
		 * 		newProxyInstance() 方法帮我们创建一个jdk动态代理对象实例<br/>
		 * 		JdkProxy代理对象是目标对象接口的一个实现类,换而言之。就是代理对象也实现了目标对象所有接口<br/>
		 * 第一个参数是：被代理的对象的类加载器<br/>
		 * 第二个参数是: 被代理的对象的接口<br/>
		 * 第三个参数是：InvocationHandler接口。它是代理对象调用方法时专门用来做增强操作的拦截接口<br/>
		 */
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
			/**
			 * invoke方法，在每次代理对象调用方法时，都会拦截到，并调用。<br/>
			 * 第一个参数是，代理对象<br/>
			 * 第二个参数是：调用的方法的反射对象<br/>
			 * 第三个参数是：调用方法时传递的参数<br/>
			 */
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Object result = null;
				//调用原目标对象的方法
				// System.out.println("当前方法是：" + method);
				// System.out.println("当前方法参数是：" + Arrays.asList(args));
				try {
					LogUtil.logBefore(method.getName(), args);
					//调用目标方法
					//result是原方法的返回值
					result = method.invoke(target, args);
					LogUtil.logAfter(method.getName(), args);
					LogUtil.logAfterReturning(method.getName(), result);
				} catch (Exception e) {
					LogUtil.logAfterThrowing(method.getName(), e);
					throw e;
				}
				//invocationHandler接口invoke方法的返回值，就是代理方法的返回值
				return result;
			}
		});
	}
	
	public static void main(String[] args) {
		//目标对象
		Calculate calculate = new Calculator();
		//创建了一个代理对象===代理对象需要帮助目标对象，实现它自己的功能，
		//并且还要在它原有功能的基础上，做一些增强操作。
		Calculate calculateProxy = (Calculate) createJdkProxy(calculate);
		System.out.println(calculateProxy.add(100, 200));
		System.out.println(calculateProxy.div(100, 0));
	}
}
