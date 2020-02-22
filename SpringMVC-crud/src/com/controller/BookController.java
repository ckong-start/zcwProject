package com.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pojo.Book;
import com.service.BookService;

@RequestMapping("/book")
@Controller
public class BookController {
	@Autowired
	private BookService bookService;

	@RequestMapping("/list")
	public ModelAndView list(Map<String, Object> map) {
		System.out.println("list() is used");
		map.put("list", bookService.queryBooks());
		ModelAndView modelAndView = new ModelAndView("bookList");
		return modelAndView;
	}

	@RequestMapping("/add")
	public String add(Book book) {
		System.out.println("add() is used, book is " + book);
		bookService.saveBook(book);
		return "redirect:/book/list";
	}

	@RequestMapping("/update")
	public String update(Book book) {
		System.out.println("update() is used");
		bookService.updateBook(book);
		return "redirect:/book/list";
	}

	@RequestMapping("/delete")
	public String delete(Integer id) {
		System.out.println("delete() is used, id is " + id.getClass());
		bookService.deleteBookById(id);
		return "redirect:/book/list";
	}

	@RequestMapping("/getBook")
	public String getBook(Integer id, Map<String, Object> map) {
		System.out.println("getBook() is used, id is " + id);
		map.put("book", bookService.queryBookById(id));
		return "bookEdit";
	}
	@RequestMapping("/test")
	public String testException() {
		System.out.println("这是目标方法");
		int i = 12 / 0;
		return "redirect:/index.jsp";
	}
}
