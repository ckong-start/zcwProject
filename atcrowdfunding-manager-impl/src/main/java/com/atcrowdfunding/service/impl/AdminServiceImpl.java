package com.atcrowdfunding.service.impl;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.atcrowdfunding.bean.TAdmin;
import com.atcrowdfunding.bean.TAdminExample;
import com.atcrowdfunding.bean.TAdminExample.Criteria;
import com.atcrowdfunding.bean.TAdminRoleExample;
import com.atcrowdfunding.exception.AdminAccountException;
import com.atcrowdfunding.mapper.TAdminMapper;
import com.atcrowdfunding.mapper.TAdminRoleMapper;
import com.atcrowdfunding.service.TAdminService;
import com.atcrowdfunding.utils.AppDateUtils;
import com.atcrowdfunding.utils.MD5Util;

@Service
public class AdminServiceImpl implements TAdminService {

	@Autowired
	TAdminMapper adminMapper;
	@Autowired
	TAdminRoleMapper adminRoleMapper;

	@Override
	public TAdmin doLogin(TAdmin loginAdmin) {
		TAdminExample example = new TAdminExample();
		// 添加当用户名和密码同时相同时的条件
		example.createCriteria().andLoginacctEqualTo(loginAdmin.getLoginacct())
				.andUserpswdEqualTo(MD5Util.digest(loginAdmin.getUserpswd()));//由于使用了md5加密方式，登录密码也需要用工具类转化一下
		List<TAdmin> list = adminMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			// 集合为空时，返回null
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<TAdmin> getAllAdmins(String condition) {
		//如果传入的条件是空，则查询所有
		if (StringUtils.isEmpty(condition)) {
			return adminMapper.selectByExample(null);
		}
		//传入的传入的条件不为空
		TAdminExample example = new TAdminExample();
		//根据传入的条件添加筛选，如账号、名字、邮箱
		Criteria c1 = example.createCriteria();
		c1.andLoginacctLike("%" + condition + "%");
		
		Criteria c2 = example.createCriteria();
		c2.andEmailLike("%" + condition + "%");
		
		Criteria c3 = example.createCriteria();
		c3.andUsernameLike("%" + condition + "%");
		example.or(c2);
		example.or(c3);
		return adminMapper.selectByExample(example);
	}

	@Override
	public void deleteAdminById(Integer id) {
		adminMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public void batchDelAdmins(List<Integer> ids) {
		TAdminExample example = new TAdminExample();
		example.createCriteria().andIdIn(ids);
		adminMapper.deleteByExample(example);
		
	}

	@Override
	public void addAdmin(TAdmin admin) {
		//验证用户账号与邮箱的唯一性，需要添加判断
		TAdminExample example = new TAdminExample();
		example.createCriteria().andLoginacctEqualTo(admin.getLoginacct());
		long countByLoginacct = adminMapper.countByExample(example);
		//判断账号相同的个数是否大于0，即查询账号是否存在
		if (countByLoginacct > 0) {
			throw new AdminAccountException("login is used");
		}
		//清除条件，使变量复用
		example.clear();
		example.createCriteria().andEmailEqualTo(admin.getEmail());
		long countByEmail = adminMapper.countByExample(example);
		//查询邮箱是否存在
		if (countByEmail > 0) {
			throw new AdminAccountException("email is used");
		}
		
		//为传入进来的对象添加时间参数，使用工具类
		admin.setCreatetime(AppDateUtils.getFormatTime());
		//使用MD5加密保存密码
		admin.setUserpswd(MD5Util.digest(admin.getUserpswd()));
		adminMapper.insertSelective(admin);
		
	}

	@Override
	public TAdmin getAdmin(Integer id) {
		return adminMapper.selectByPrimaryKey(id);
		
	}

	@Override
	public void updateAdmin(TAdmin admin) {
		//先获取之前的admin信息做比较
		TAdmin oldAdmin = this.getAdmin(admin.getId());
		//验证用户账号与邮箱的唯一性，需要添加判断
		TAdminExample example = new TAdminExample();
		if (!oldAdmin.getLoginacct().equals(admin.getLoginacct())) {
			example.createCriteria().andLoginacctEqualTo(admin.getLoginacct());
			long countByLoginacct = adminMapper.countByExample(example);
			//判断账号相同的个数是否大于0，即查询账号是否存在
			if (countByLoginacct > 0) {
				throw new AdminAccountException("loginacct is used");
			}
		}
		if (!oldAdmin.getEmail().equals(admin.getEmail())) {
			//清除条件，使变量复用
			example.clear();
			example.createCriteria().andEmailEqualTo(admin.getEmail());
			long countByEmail = adminMapper.countByExample(example);
			//查询邮箱是否存在
			if (countByEmail > 0) {
				throw new AdminAccountException("email is used");
			}
		}
		adminMapper.updateByPrimaryKeySelective(admin);
		
	}

	@Override
	public void assignedRolesToAdmin(Integer adminId, List<Integer> ids) {
		adminRoleMapper.batchInsertRolesToAdmin(adminId, ids);
		
	}

	@Override
	public void unAssignedRolesToAdmin(Integer adminId, List<Integer> ids) {
		TAdminRoleExample example = new TAdminRoleExample();
		example.createCriteria().andAdminidEqualTo(adminId).andRoleidIn(ids);
		adminRoleMapper.deleteByExample(example);
		
	}

}
