package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pojo.Book;
import com.service.BookService;

@RequestMapping("/book")
@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list() {
		List<Book> books = bookService.queryBooks();
		ModelAndView modelAndView = new ModelAndView("bookList");
		modelAndView.addObject("books", books);
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String saveBook(Book book) {
		bookService.saveBook(book);
		return "redirect:/book/list";
	}
}
