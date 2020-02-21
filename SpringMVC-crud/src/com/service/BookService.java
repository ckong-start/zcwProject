package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BookDao;
import com.pojo.Book;

@Service
public class BookService {
	@Autowired
	private BookDao bookDao;

	public int saveBook(Book book) {
		return bookDao.saveBook(book);
	}

	public int updateBook(Book book) {
		return bookDao.updateBook(book);
	}

	public int deleteBookById(Integer id) {
		return bookDao.deleteBook(id);
	}

	public Book queryBookById(Integer id) {
		return bookDao.queryBookById(id);
	}

	public List<Book> queryBooks() {
		return bookDao.queryBooks();
	}
}
