package com.atcrowdfunding.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.atcrowdfunding.bean.TAdmin;
import com.atcrowdfunding.bean.TAdminExample;
import com.atcrowdfunding.mapper.TAdminMapper;
import com.atcrowdfunding.mapper.TPermissionMapper;
import com.atcrowdfunding.mapper.TRoleMapper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	TAdminMapper adminMapper;
	@Autowired
	TRoleMapper roleMapper;
	@Autowired
	TPermissionMapper permissionMapper;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//根据账号查询管理员
		TAdminExample example = new TAdminExample();
		example.createCriteria().andLoginacctEqualTo(username);
		List<TAdmin> admins = adminMapper.selectByExample(example);
		if (admins.size() > 1 || CollectionUtils.isEmpty(admins)) {
			return null;
		}
		TAdmin admin = admins.get(0);
		Integer adminId = admin.getId();
		//根据管理员id查询管理员的权限和角色集合
		//mybatis两表联查需要手动编写sql
		List<String> roleNames = roleMapper.selectRoleNamesByAdminId(adminId);
		List<String> permissionNames = permissionMapper.selectPermissionNamesByAdminId(adminId);
		//创建权限集合
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(roleNames.size() + permissionNames.size());

		for (String roleName : roleNames) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + roleName));
		}
		for (String permissionName : permissionNames) {
			authorities.add(new SimpleGrantedAuthority(permissionName));
		}
		logger.debug("拥有的权限{}",authorities);
		//创建主体对象
		return new User(admin.getLoginacct(), admin.getUserpswd(), authorities);
	}

}
