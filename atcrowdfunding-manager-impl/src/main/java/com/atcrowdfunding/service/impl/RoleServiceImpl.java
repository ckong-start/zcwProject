package com.atcrowdfunding.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.atcrowdfunding.bean.TAdminRole;
import com.atcrowdfunding.bean.TAdminRoleExample;
import com.atcrowdfunding.bean.TPermission;
import com.atcrowdfunding.bean.TRole;
import com.atcrowdfunding.bean.TRoleExample;
import com.atcrowdfunding.bean.TRolePermissionExample;
import com.atcrowdfunding.mapper.TAdminRoleMapper;
import com.atcrowdfunding.mapper.TPermissionMapper;
import com.atcrowdfunding.mapper.TRoleMapper;
import com.atcrowdfunding.mapper.TRolePermissionMapper;
import com.atcrowdfunding.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	TRoleMapper roleMapper;
	@Autowired
	TAdminRoleMapper adminRoleMapper;
	@Autowired
	TPermissionMapper permissionMapper;
	@Autowired
	TRolePermissionMapper rolePermissionMapper;
	
	@Override
	public List<TRole> getRoles(String condition) {
		if (StringUtils.isEmpty(condition) || condition.length() < 1) {
			return roleMapper.selectByExample(null);
		}
		TRoleExample example = new TRoleExample();
		example.createCriteria().andNameLike("%" + condition + "%");
		return roleMapper.selectByExample(example);
	}

	@Override
	public void delRoleById(Integer id) {
		roleMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public TRole getRole(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
		
	}

	@Override
	public void updateRole(TRole role) {
		roleMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public void addRole(TRole role) {
		roleMapper.insertSelective(role);
	}

	@Override
	public void batchDelRoles(List<Integer> id) {
		TRoleExample example = new TRoleExample();
		example.createCriteria().andIdIn(id);
		roleMapper.deleteByExample(example);
		
	}

	@Override
	public List<Integer> getRoleIdsByAdminId(Integer adminId) {
		TAdminRoleExample example = new TAdminRoleExample();
		example.createCriteria().andAdminidEqualTo(adminId);
		List<TAdminRole> list = adminRoleMapper.selectByExample(example);
		//创建集合存入已分配的角色id
		List<Integer> ids = new ArrayList<Integer>();
		for (TAdminRole tAdminRole : list) {
			ids.add(tAdminRole.getRoleid());
		}
		return ids;
	}

	@Override
	public List<TPermission> getPermissions() {
		return permissionMapper.selectByExample(null);
	}

	@Override
	public List<Integer> getAssignedPermissionids(Integer id) {
		//select permissionid from t_role_permission where roleid = #{id};
		return rolePermissionMapper.selectAssignedPermissionidsByRoleid(id);
	}

	@Override
	public void delRolePermissions(Integer roleId) {
		TRolePermissionExample example = new TRolePermissionExample();
		example.createCriteria().andRoleidEqualTo(roleId);
		rolePermissionMapper.deleteByExample(example);
	}

	@Override
	public void assignPermissionsToRole(Integer roleId, List<Integer> ids) {
		rolePermissionMapper.insertAssignPermissionsToRole(roleId, ids);
	}

}
