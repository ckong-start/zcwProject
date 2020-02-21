package exer.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import exer.pojo.Calculate;

@ContextConfiguration(locations="classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest {
	@Autowired
	private Calculate calculate;
	
	
	@Test
	public void test1() throws Exception {
		System.out.println(calculate.getClass());
		calculate.add(100, 200);
		System.out.println("----------");
		calculate.div(100, 0);
	}
	
	@Test
	public void test2() throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop-xml.xml");
		Calculate calculate = (Calculate) applicationContext.getBean("calculate");
		calculate.add(100, 200);
		System.out.println("---------------");
		calculate.div(100, 0);
	}
}
