package test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.ibatis.jdbc.Null;
import org.junit.Test;

import pojo.User;

public class UserTest {
	@Test
	public void test() throws Exception {
		User user = new User(null, "0", null);
		User user2 = testT(user);
		System.out.println(user2);
	}
	@Test
	public void test1() throws Exception {
		String str = "id";
		String newStr = getGetName(str);
		System.out.println(newStr);
	}
	
	private <T> T testT(T t) throws Exception {
		Class clazz = t.getClass();
		
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Method method = clazz.getDeclaredMethod(getGetName(field.getName()), null);
			Object result = method.invoke(t);
			if (result == null) {
				Method method2 = clazz.getDeclaredMethod(getSetName(field.getName()), field.getType());
				method2.invoke(t, 0);
			}
		}
		return t;
	}
	private String getGetName(String str) {
		String newStr = "get" + (char)(str.charAt(0) - 32) + str.substring(1);
		return newStr;
	}
	private String getSetName(String str) {
		String newStr = "set" + (char)(str.charAt(0) - 32) + str.substring(1);
		return newStr;
	}
}
