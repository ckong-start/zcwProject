package com.scw.webui.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.scw.common.bean.AppResponse;
import com.scw.webui.bean.TMemberAddress;
import com.scw.webui.service.UserServiceFeignClient;
@Service
public class UserServiceFeignClientExceptionHandler implements UserServiceFeignClient{

	@Override
	public AppResponse<Object> doLogin(String loginacct, String userpswd) {
		
		return AppResponse.fail("20000", "服务器繁忙，请重试");
	}

	@Override
	public String logout(HttpSession session) {
		return "error/502";
	}

	@Override
	public AppResponse<List<TMemberAddress>> getAddress(@RequestParam("accessToken")String accessToken) {
		return AppResponse.fail("20000", "服务器繁忙，请重试");
	}

	@Override
	public AppResponse<Integer> getMemberid(String accessToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppResponse<Object> sendSms(String phoneNum) {
		
		return AppResponse.fail("10050", "发送失败，请重试");
	}

}
