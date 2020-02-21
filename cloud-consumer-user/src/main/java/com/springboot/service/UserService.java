package com.springboot.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bean.User;
import com.springboot.dao.UserDao;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

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
		result.put("movie", null);// 暂时为null
		return result;
	}
}
