package com.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pojo.Book;
import com.service.BookService;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	//显式图书
	@RequestMapping(value="/book", method=RequestMethod.GET)
	public String list(Map<String, Object> map) {
		System.out.println("list() is used");
		map.put("list", bookService.queryBooks());
		return "bookList";
	}
	//添加图书
	@RequestMapping(value="/book", method=RequestMethod.POST)
	public String add(Book book) {
		System.out.println("add() is used, book is " + book);
		bookService.saveBook(book);
		return "redirect:/book";
	}
	//修改图书
	@RequestMapping(value="/book", method=RequestMethod.PUT)
	public String update(Book book) {
		System.out.println("update() is used" + book);
		bookService.updateBook(book);
		return "redirect:/book";
	}
	//删除图书
	@RequestMapping(value="/book/{deleteId}", method=RequestMethod.DELETE)
	public String delete(@PathVariable("deleteId") Integer id) {
		System.out.println("delete() is used, id is " + id);
		bookService.deleteBookById(id);
		return "redirect:/book";
	}
	//根据id获取图书
	@RequestMapping(value="/book/{queryBookById}", method=RequestMethod.GET)
	public String getBook(@PathVariable("queryBookById") Integer id, Map<String, Object> map) {
		System.out.println("getBook() is used, id is " + id);
		map.put("book", bookService.queryBookById(id));
		return "bookEdit";
	}
}
