package com.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.bean.TAdmin;
import com.springboot.mapper.TAdminMapper;

@Controller
public class HelloController {
	@Autowired
	TAdminMapper adminMapper;
	
	@ResponseBody
	@GetMapping("/getAdmin")
	public TAdmin getAdmin(Integer id) {
		return adminMapper.getAdminById(id);
	}
	
}
