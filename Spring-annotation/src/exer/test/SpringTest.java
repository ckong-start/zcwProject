package exer.test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import exer.dao.BookDao;
import exer.pojo.Book;
import exer.service.BookService;
@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest {
	@Autowired
	private Book book;
	@Autowired
	private BookService bookService;
	
	@Test
	public void test1() throws Exception {
		System.out.println(book);
		System.out.println(bookService);
	}
	
	@Test
	public void test() throws Exception {
		String className = "exer.pojo.Book";
		Class<?> forName = Class.forName(className);
		Object newInstance = forName.newInstance();
		BookDao bookDao = new BookDao();
		Field[] fields = forName.getDeclaredFields();
		for (Field field : fields) {
			//System.out.println(field);
			Autowired annotation = field.getAnnotation(Autowired.class);
			if(annotation != null) {
				System.out.println(field + "=======" + annotation);
				
				field.setAccessible(true);
				field.set(newInstance, bookDao);
			}else {
				System.out.println(field + " 没有注解@Autowired");
			}
		}
		System.out.println(newInstance);
	}
}
