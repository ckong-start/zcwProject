package test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pojo.Person;

public class ELTest {
	@Test
	public void test1() throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		Person person = (Person) applicationContext.getBean("person");
		System.out.println(person);
	}
}
