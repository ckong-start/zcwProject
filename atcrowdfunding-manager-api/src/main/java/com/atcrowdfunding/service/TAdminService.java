package com.atcrowdfunding.service;

import java.util.List;

import com.atcrowdfunding.bean.TAdmin;

public interface TAdminService {
	//登录的方法
	TAdmin doLogin(TAdmin loginAdmin);
	//查询所有的管理员用户
	List<TAdmin> getAllAdmins(String condition);
	//根据id删除管理员
	void deleteAdminById(Integer id);
	//批量删除管理员
	void batchDelAdmins(List<Integer> ids);
	//添加管理员
	void addAdmin(TAdmin admin);
	//根据id获取管理员信息
	TAdmin getAdmin(Integer id);
	//更新管理员信息
	void updateAdmin(TAdmin admin);
	//根据用户id给其分配角色
	void assignedRolesToAdmin(Integer adminId, List<Integer> ids);
	//根据用户id取消其已分配的角色
	void unAssignedRolesToAdmin(Integer adminId, List<Integer> ids);

}
