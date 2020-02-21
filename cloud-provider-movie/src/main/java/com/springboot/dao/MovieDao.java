package com.springboot.dao;

import org.springframework.stereotype.Repository;

import com.springboot.bean.Movie;

@Repository
public class MovieDao {
	
	public Movie getMovie(Integer id) {
		Movie movie = new Movie();
		movie.setId(id);
		movie.setMovieName("唐人街探案");
		return movie;
	}
}
