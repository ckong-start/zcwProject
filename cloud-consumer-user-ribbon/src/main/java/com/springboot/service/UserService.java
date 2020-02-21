package com.springboot.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springboot.bean.Movie;
import com.springboot.bean.User;
import com.springboot.dao.UserDao;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	@Autowired
	RestTemplate restTemplate;

	public User getUserById(Integer id) {
		User user = userDao.getUser(id);
		return user;
	}

	/**
	 * 查询用户和最新的电影票 传入用户id
	 */
	public Map<String, Object> getUserAndMovie(Integer id) {
		Map<String, Object> result = new HashMap<>();
		// 1、查询用户信息
		User user = getUserById(id);
		// 2、查到最新电影票
		result.put("user", user);
		Movie movie = restTemplate.getForObject("http://CLOUD-PROVIDER-MOVIE/movie", Movie.class);
		result.put("movie", movie);// 暂时为null
		return result;
	}
}
