package com.atcrowdfunding.service;

import java.util.List;

import com.atcrowdfunding.bean.TPermission;
import com.atcrowdfunding.bean.TRole;

public interface RoleService {

	//获取所有的角色数据
	List<TRole> getRoles(String condition);
	//根据id删除角色
	void delRoleById(Integer id);
	//根据id查询角色
	TRole getRole(Integer id);
	//修改角色信息
	void updateRole(TRole role);
	//新增角色信息
	void addRole(TRole role);
	//通过id批量删除角色
	void batchDelRoles(List<Integer> id);
	//根据用户id查询已分配的角色ids
	List<Integer> getRoleIdsByAdminId(Integer adminId);
	//获取所有的权限集合
	List<TPermission> getPermissions();
	//根据角色id查询已分配的权限集合
	List<Integer> getAssignedPermissionids(Integer id);
	//根据角色id删除原来的权限
	void delRolePermissions(Integer roleId);
	//给角色分配权限
	void assignPermissionsToRole(Integer roleId, List<Integer> ids);
	

}
