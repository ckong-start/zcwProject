package com.springboot.service;

import org.springframework.stereotype.Component;

import com.springboot.bean.Movie;
import com.springboot.feign.MovieServiceFeign;
@Component
public class MovieFeignExceptionHandlerService implements MovieServiceFeign {
	/** 远程这个方法调用出问题就会调用此方法 */
	@Override
	public Movie getMovie() {
		Movie movie = new Movie();
		movie.setId(-100);
		movie.setMovieName("无此电影呀...");
		return movie;
	}

}
