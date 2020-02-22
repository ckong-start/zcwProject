package com.service;

import java.util.List;

import com.pojo.Book;

public interface BookService {

	int saveBook(Book book);
	int udpateBook(Book book);
	int deleteBookById(Integer id);
	Book queryBookById(Integer id);
	List<Book> queryBooks();
	
}
