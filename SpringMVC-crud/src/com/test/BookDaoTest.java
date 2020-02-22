package com.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dao.BookDao;
import com.pojo.Book;

import sun.print.resources.serviceui_zh_TW;

@ContextConfiguration(locations = "classpath:springmvc.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BookDaoTest {
	@Autowired
	private BookDao bookDao;

	@Test
	public void testSaveBook() {
		int i = bookDao.saveBook(new Book(null, "国哥在手，天下我有", "1010", new BigDecimal(10000000), 111111111, 0));
		System.out.println(i);
	}

	@Test
	public void testUpdateBook() {
		int i = bookDao.updateBook(new Book(21, "怎么样拐跑别人媳妇", "国哥", new BigDecimal(10000000), 0, 11111110));
		System.out.println(i);
	}

	@Test
	public void testDeleteBook() {
		int i = bookDao.deleteBook(21);
		System.out.println(i);
	}

	@Test
	public void testQueryBookById() {
		Book book = bookDao.queryBookById(15);
		System.out.println(book);
	}

	@Test
	public void testQueryBooks() {
		bookDao.queryBooks().forEach(System.out::println);
	}

}
