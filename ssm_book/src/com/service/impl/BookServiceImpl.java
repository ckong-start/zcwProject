package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.BookMapper;
import com.pojo.Book;
import com.service.BookService;
@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookMapper bookMapper;
	@Override
	public int saveBook(Book book) {
		return bookMapper.insert(book);
	}

	@Override
	public int udpateBook(Book book) {
		return bookMapper.updateByPrimaryKey(book);
	}

	@Override
	public int deleteBookById(Integer id) {
		return bookMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Book queryBookById(Integer id) {
		return bookMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Book> queryBooks() {
		return bookMapper.selectByExample(null);
	}

}
