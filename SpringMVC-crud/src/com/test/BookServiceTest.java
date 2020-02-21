package com.test;

import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pojo.Book;
import com.service.BookService;
@ContextConfiguration(locations="classpath:springmvc.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceTest {
	@Autowired
	private BookService bookService;
	@Test
	public void testSaveBook() {
		bookService.saveBook(new Book(null, "国哥要一亿对象", "心声", new BigDecimal(1234211), 1111111111, 0));
	}

	@Test
	public void testUpdateBook() {
		int i = bookService.updateBook(new Book(22, "国哥觉得一亿个已经不够了", "心声", new BigDecimal(0), 1111111111, 0));
		System.out.println(i);
	}

	@Test
	public void testDeleteBookById() {
		int i = bookService.deleteBookById(22);
		System.out.println(i);
	}

	@Test
	public void testQueryBookById() {
		System.out.println(bookService.queryBookById(14));
	}

	@Test
	public void testQueryBooks() {
		bookService.queryBooks().forEach(System.out::println);
	}

}
