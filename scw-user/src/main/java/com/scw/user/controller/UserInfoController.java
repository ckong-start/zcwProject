package com.scw.user.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scw.common.bean.AppResponse;
import com.scw.common.utils.ScwAppUtils;
import com.scw.user.bean.TMember;
import com.scw.user.bean.TMemberAddress;
import com.scw.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(tags="用户个人信息模块")
@Slf4j
public class UserInfoController {
	@ApiOperation(value="获取个人信息")
	@GetMapping("/user/info/")
	public String getUser() {
		
		return null;
	}
	
	@ApiOperation(value="更新个人信息")
	@PostMapping("/user/info/")
	public String updateUser() {
		
		return null;
	}
	
	@Autowired
	UserService userService;
	@Autowired
	StringRedisTemplate srt;
	
	@ApiOperation(value="获取用户收获地址")
	@GetMapping("/user/info/address")
	public AppResponse<List<TMemberAddress>> getAddress(@RequestParam("accessToken")String accessToken) {
		String token = "login:" + accessToken + ":token";
		//1、根据accessToken查询redis中的用户对象
		TMember member = ScwAppUtils.jsonStr2Obj(TMember.class, token, srt);
		log.info("查询到的用户：{}", member);
		Integer memberid = member.getId();
		List<TMemberAddress> addresses = userService.getAllAddress(memberid);
		return AppResponse.ok(addresses);
	}
	
	@GetMapping("/user/info/getId")
	public AppResponse<Integer> getMemberid(String accessToken){
		String token = "login:" + accessToken + ":token";
		TMember member = ScwAppUtils.jsonStr2Obj(TMember.class, token, srt);
		return AppResponse.ok(member.getId());
	}
	
	@ApiOperation(value="新增用户收获地址")
	@PostMapping("/user/info/address")
	public String addAddress() {
		
		return null;
	}
	
	@ApiOperation(value="修改用户收获地址")
	@PutMapping("/user/info/address")
	public String updateAddress() {
		
		return null;
	}
	
	@ApiOperation(value="删除用户收获地址")
	@DeleteMapping("/user/info/address")
	public String delAddress() {
		
		return null;
	}
	
	@ApiOperation(value="获取我发起的项目")
	@GetMapping("/user/info/create/project")
	public String getCreateProject() {
		
		return null;
	}
	
	@ApiOperation(value="获取我的系统信息")
	@GetMapping("/user/info/message")
	public String getMessage() {
		
		return null;
	}
	
	@ApiOperation(value="查看我的订单")
	@GetMapping("/user/info/order")
	public String getOrder() {
		
		return null;
	}
	
	@ApiOperation(value="删除我的订单")
	@DeleteMapping("/user/info/order")
	public String delOrder() {
		
		return null;
	}
	
	@ApiOperation(value="获取我关注的项目")
	@GetMapping("/user/info/star/project")
	public String getStarProject() {
		
		return null;
	}
	
	@ApiOperation(value="获取我支持的项目")
	@GetMapping("/user/info/support/project")
	public String getSupportProject() {
		
		return null;
	}
}
