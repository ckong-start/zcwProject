package com.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bean.Movie;
import com.springboot.dao.MovieDao;

@Service
public class MovieService {

	@Autowired
	MovieDao movieDao;
	
	public Movie getNewMovie(Integer id) {
		return movieDao.getMovie(id);
	}
}
