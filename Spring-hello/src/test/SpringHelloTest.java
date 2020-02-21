package test;

import static org.junit.Assert.*;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;import com.sun.org.apache.xml.internal.security.c14n.implementations.Canonicalizer20010315;

import pojo.Car;
import pojo.Person;
import sun.print.resources.serviceui_zh_TW;

public class SpringHelloTest {
	@Test
	public void test1() throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		Person p1 = (Person) applicationContext.getBean("p1");
		Person p2 = (Person) applicationContext.getBean("p1");
		System.out.println(p1 == p2);
	}
	@Test
	public void test2() throws Exception {
		FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext("config/applicationContext.xml");
		Person person = applicationContext.getBean(Person.class);
		System.out.println(person);
	}
	
	@Test
	public void test3() throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println(applicationContext.getBean("p18"));
	}
	
	@Test
	public void test4() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println(applicationContext.getBean("p18"));
		applicationContext.close();
	}
	
	@Test
	public void test5() throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		Car car = (Car) applicationContext.getBean("car");
		Person bean1 = (Person) applicationContext.getBean("p12");
		Person bean2 = (Person) applicationContext.getBean("p12");
		System.out.println(bean1 == bean2);
		System.out.println(car);
	}

}
