package com.scw.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="用户实名审核模块")
@RestController
public class UserRealAuthController {

	@ApiOperation(value="认证申请第2步-提交基本信息")
	@PostMapping("/user/auth/baseinfo")
	public String baseInfo() {
		
		return null;
	}
	
	@ApiOperation(value="认证申请第3步-上传资质信息")
	@PostMapping("/user/auth/certs")
	public String certs() {
		
		return null;
	}
	
	@ApiOperation(value="获取需要上传的资质信息")
	@GetMapping("/user/auth/certs2upload")
	public String certsToUpload() {
		
		return null;
	}

	@ApiOperation(value="认证申请第4步-确认邮箱信息")
	@PostMapping("/user/auth/email")
	public String email() {
		
		return null;
	}
	
	@ApiOperation(value="认证申请第1步-用户认证申请开始")
	@GetMapping("/user/auth/start")
	public String start() {
		
		return null;
	}
	
	@ApiOperation(value="认证申请第5步-提交实名认证申请")
	@PostMapping("/user/auth/submit")
	public String submit() {
		
		return null;
	}
}
