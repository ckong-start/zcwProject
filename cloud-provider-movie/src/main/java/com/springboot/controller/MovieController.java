package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bean.Movie;
import com.springboot.service.MovieService;

@RestController
public class MovieController {

	@Autowired
	MovieService movieService;
	@Value("${server.port}")
	Integer port;
	
	@GetMapping("/movie")
	public Movie getMovie() {
		System.out.println("端口号：" + port + " 被调用了");
		return movieService.getNewMovie(1);
	}
}
