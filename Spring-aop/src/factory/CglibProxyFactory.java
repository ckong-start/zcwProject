package factory;

import java.lang.reflect.Method;

import exer.pojo.Calculator;
import exer.util.LogUtil;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxyFactory {
	public static Object createCglibProxy(Object target) {
		//增强器
		Enhancer enhancer = new Enhancer();
		//设置代理对象的具体类型===方便在指定的Class类型上生成具体的子类
		enhancer.setSuperclass(target.getClass());
		/**
		 * 专门用来拦截代理方法调用。并在此做增强操作<br/>
		 * MethodInterceptor接口是用来拦截代理方法调用，用于产生增强的拦截接口<br/>
		 */
		enhancer.setCallback(new MethodInterceptor() {
			/**
			 * intercept方法是每次调用代理方法时，就会拦截代理方法的调用。并在此实现增强操作<br/>
			 * 	Object proxy是代理对象<br/>
			 *  Method method是调用方法的反射对象<br/>
			 *  Object[] args是调用方法时传递的参数 <br/>
			 *  MethodProxy methodProxy是方法反射对象的代理对象<br/>
			 */
			@Override
			public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy)
					throws Throwable {
				Object result = null;
				try {
					LogUtil.logBefore(method.getName(), args);
					result = method.invoke(target, args);
					LogUtil.logAfter(method.getName(), args);
					LogUtil.logAfterReturning(method.getName(), result);
				} catch (Exception e) {
					LogUtil.logAfterThrowing(method.getName(), e);
					throw e;
				}
				return result;
			}
		});
		
		//创建代理对象实例
		return enhancer.create();
	}
	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		Calculator calculatorProxy = (Calculator) createCglibProxy(calculator);
		System.out.println(calculatorProxy.div(100, 0));
	}
}
