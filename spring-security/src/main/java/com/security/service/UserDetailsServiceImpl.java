package com.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//当用户登陆时，创建主体时，springsecurity会自动调用此方法处理主体创建的业务，并传入登录账号
		//1.根据账号查询用户信息
		String sql = "select * from t_admin where loginacct = ?";
		Map<String, Object> admin = jdbcTemplate.queryForMap(sql, username);
		System.out.println(admin);
		//2.根据用户的id查询角色和权限集合
		//2.1查询角色集合
		Integer adminId = (Integer) admin.get("id");
		sql = "select tr.* from t_admin_role tar join t_role tr on tar.roleid = tr.id where adminid = ?";
		List<Map<String, Object>> roleList = jdbcTemplate.queryForList(sql, adminId);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		System.out.println(roleList);
		//遍历所有的角色，讲角色存入到权限集合中
		for (Map<String, Object> role : roleList) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.get("name")));
			//获取正在遍历的角色的权限集合，存入到权限集合中
			Integer roleId = (Integer) role.get("id");
			//2.2查询权限集合
			sql = "select tp.name from t_role_permission trp join t_permission tp on trp.permissionid = tp.id where trp.roleid = ? and tp.name is not null";
			List<String> permissionNameList = jdbcTemplate.queryForList(sql, String.class, roleId);
			for (String permissionName : permissionNameList) {
				//遍历所有的权名称存入到权限集合中
				authorities.add(new SimpleGrantedAuthority(permissionName));
			}
		}
		
		//3.将用户信息和权限角色集合创建为主体对象
		return new User(admin.get("loginacct").toString(), admin.get("userpswd").toString(), authorities);
	}

}
