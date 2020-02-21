package com.atcrowdfunding.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atcrowdfunding.bean.TPermission;
import com.atcrowdfunding.bean.TRole;
import com.atcrowdfunding.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RequestMapping("/role")
@Controller
public class RoleController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	RoleService roleService;
	
	// 10.处理异步请求分配权限给角色的方法
	@ResponseBody
	@PostMapping("/assignPermissionsToRole")
	public String assignPermissionsToRole(Integer roleId, @RequestParam("ids")List<Integer> ids) {
		//先删除当前角色的所有权限
		roleService.delRolePermissions(roleId);
		//再保存传过来的权限
		roleService.assignPermissionsToRole(roleId, ids);
		return "ok";
	}
	
	// 9.根据角色id查询已分配的权限id集合
	@ResponseBody
	@GetMapping("/getAssignedPermissionids")
	public List<Integer> getAssignedPermissionids(Integer id){
		return roleService.getAssignedPermissionids(id);
	}
	
	// 8.查询所有的权限
	@ResponseBody
	@GetMapping("/getPermissions")
	public List<TPermission> getPermissions(){
		return roleService.getPermissions();
	}
	
	// 7.根据获取的id批量删除角色
	@ResponseBody
	@GetMapping("/batchDelRoles")
	public String batchDelRoles(@RequestParam("ids")List<Integer> ids) {
		roleService.batchDelRoles(ids);
		return "ok";
	}
	
	// 6.新增角色信息
	@ResponseBody
	@PostMapping("/addRole")
	public String addRole(TRole role) {
		//logger.debug("新增角色的信息：{}", role);
		roleService.addRole(role);
		return "add";
	}
	
	// 5.修改角色信息
	@ResponseBody
	@PostMapping("/updateRole")
	public String updateRole(TRole role) {
		//logger.debug("修改角色的信息：{}", role);
		roleService.updateRole(role);
		return "update";
	}
	
	// 4.异步发送请求根据id获取角色信息
	@ResponseBody
	@GetMapping("/getRole")
	public TRole getRole(Integer id) {
		return roleService.getRole(id);
	}
	
	// 3.异步根据id删除角色的方法
	@PreAuthorize("hasAnyRole('PG - 程序员')")
	@ResponseBody
	@GetMapping("/delRole")
	public String delRole(Integer id) {
		roleService.delRoleById(id);
		return "ok";
	}
	
	// 2.异步查询角色分页数据的方法
	@ResponseBody
	@GetMapping("/getRoles")
	public PageInfo<TRole> getRoles(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(required = false, defaultValue = "") String condition) {
		// 启动分页查询
		PageHelper.startPage(pageNum, 3);
		// 查询分页数据
		List<TRole> roles = roleService.getRoles(condition);
		PageInfo<TRole> pageInfo = new PageInfo<TRole>(roles, 3);
		// 将分页数据回传给ajax请求
		return pageInfo;
	}
	
	// 1.转发角色维护页面
	@GetMapping("/role.html")
	public String toRolePage() {
		return "role/role";
	}
}
