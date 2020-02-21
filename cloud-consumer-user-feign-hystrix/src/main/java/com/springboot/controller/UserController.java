package com.springboot.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bean.User;
import com.springboot.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/user")
	public User getUser(@RequestParam("id") Integer id) {
		User user = userService.getUserById(id);
		return user;
	}

	@GetMapping("/getMovie")
	public Map<String, Object> buyMovie(@RequestParam("id") Integer userid) {
		Map<String, Object> map = userService.getUserAndMovie(userid);
		return map;
	}

}
