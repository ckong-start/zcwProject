package com.scw.user.service;

import java.util.List;

import com.scw.user.bean.TMember;
import com.scw.user.bean.TMemberAddress;
import com.scw.user.vo.UserRegistVO;

public interface UserService {
	//根据属性注册用户
	void doRegist(UserRegistVO urvo);
	//根据账号与密码处理登录请求
	TMember doLogin(String loginacct, String userpswd);
	//根据id获取用户的收货地址
	List<TMemberAddress> getAllAddress(Integer id);

}
