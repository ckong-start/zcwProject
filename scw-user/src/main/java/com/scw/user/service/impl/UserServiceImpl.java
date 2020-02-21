package com.scw.user.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.scw.common.utils.ScwAppUtils;
import com.scw.user.bean.TMember;
import com.scw.user.bean.TMemberAddress;
import com.scw.user.bean.TMemberAddressExample;
import com.scw.user.bean.TMemberExample;
import com.scw.user.exception.UserAcctException;
import com.scw.user.mapper.TMemberAddressMapper;
import com.scw.user.mapper.TMemberMapper;
import com.scw.user.service.UserService;
import com.scw.user.vo.UserRegistVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	TMemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	TMemberAddressMapper memberAddressMapper;

	@Override
	public void doRegist(UserRegistVO urvo) {
		// 获取账号和邮箱
		String loginacct = urvo.getLoginacct();
		String email = urvo.getEmail();
		// 1.验证手机号码的唯一性
		TMemberExample example = new TMemberExample();
		example.createCriteria().andLoginacctEqualTo(loginacct);
		long countByAcct = memberMapper.countByExample(example);
		if (countByAcct > 0) {
			throw new UserAcctException("手机号码已被注册");
		}
		// 2.验证邮箱的唯一性
		example.clear();
		example.createCriteria().andEmailEqualTo(email);
		long countByEmail = memberMapper.countByExample(example);
		if (countByEmail > 0) {
			throw new UserAcctException("邮箱已被注册");
		}
		// 3.保存注册信息到数据库中
		// 如果urvo的属性名称以及数据类型 和 member对象的属性名数据类型完全一样，此方法可以将urvo的属性值对拷给member对象
		// loginacct,userpswd,email,usertype 拷贝成功
		// username、authstatus(账户的认证状态:0-未认证 1-已认证) 数据库表不能为null ，需要手动设置默认值
		TMember member = new TMember();
		BeanUtils.copyProperties(urvo, member);
		member.setUsername(loginacct);
		// 密码加密：BCrptPasswordEncoder springSecurity框架提供该工具 ,springboot底层默认引入的base
		// security
		member.setUserpswd(passwordEncoder.encode(urvo.getUserpswd()));
		member.setAuthstatus("0");
		memberMapper.insertSelective(member);
	}

	@Override
	public TMember doLogin(String loginacct, String userpswd) {
		TMemberExample example = new TMemberExample();
		//根据账号先查询该账号对应的用户
		example.createCriteria().andLoginacctEqualTo(loginacct);
		List<TMember> selectByAcct = memberMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(selectByAcct) || selectByAcct.size() > 1) {
			throw new UserAcctException("账号不存在或错误");
		}
		TMember member = selectByAcct.get(0);
		//使用BCrypt验证密码
		boolean matchResult = passwordEncoder.matches(userpswd, member.getUserpswd());
		if (!matchResult) {
			throw new UserAcctException("密码错误");
		}
		//账号密码都正确
		return member;
	}

	@Override
	public List<TMemberAddress> getAllAddress(Integer id) {
		TMemberAddressExample example = new TMemberAddressExample();
		example.createCriteria().andMemberidEqualTo(id);
		return memberAddressMapper.selectByExample(example);
	}


}
