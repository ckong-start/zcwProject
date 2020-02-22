package com.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mapper.BookMapper;
import com.pojo.Book;
import com.service.BookService;

@ContextConfiguration(locations="classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest {
	@Autowired
	private BookMapper bookMapper;
	@Autowired
	private BookService bookService;
	@Test
	public void testQuery() throws Exception {
		Book book = bookMapper.selectByPrimaryKey(16);
		System.out.println(book);
	}
	@Test
	public void testTransaction() throws Exception {
		int i = bookService.saveBook(new Book(null, "77", "77", new BigDecimal(77), 77, 77));
		System.out.println(i);
	}
}
