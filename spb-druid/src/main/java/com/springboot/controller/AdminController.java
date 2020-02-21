package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bean.TAdmin;
import com.springboot.mapper.AdminMapper;

@RestController
public class AdminController {
	@Autowired
	AdminMapper adminMapper;

	@GetMapping("/getAdmin")
	public TAdmin getAdmin(Integer id) {
		return adminMapper.getAdminById(id);
	}
}
