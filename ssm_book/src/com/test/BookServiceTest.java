package com.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pojo.Book;
import com.service.BookService;
import com.service.impl.BookServiceImpl;
@ContextConfiguration(locations="classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceTest {
	@Autowired
	private BookService bookService = new BookServiceImpl();
	@Test
	public void testSaveBook() {
		int i = bookService.saveBook(new Book(null, "11", "11", new BigDecimal(11), 11, 11));
		System.out.println(i);
	}

	@Test
	public void testUdpateBook() {
		int i = bookService.udpateBook(new Book(21, "11", "11", new BigDecimal(11), 11, 22));
		System.out.println(i);
	}

	@Test
	public void testDeleteBookById() {
		int i = bookService.deleteBookById(21);
		System.out.println(i);
	}

	@Test
	public void testQueryBookById() {
		Book book = bookService.queryBookById(21);
		System.out.println(book);
	}

	@Test
	public void testQueryBooks() {
		bookService.queryBooks().forEach(System.out::println);
	}

}
