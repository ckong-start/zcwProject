package com.atcrowdfunding.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atcrowdfunding.bean.TAdmin;
import com.atcrowdfunding.bean.TMenu;
import com.atcrowdfunding.bean.TRole;
import com.atcrowdfunding.service.MenuService;
import com.atcrowdfunding.service.RoleService;
import com.atcrowdfunding.service.TAdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RequestMapping(value = "/admin")
@Controller
public class AdminController {
	@Autowired
	TAdminService adminService;
	@Autowired
	MenuService menuService;
	@Autowired
	RoleService roleService;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	// 12.取消用户的角色
	@ResponseBody
	@PostMapping("/unAssignedRolesToAdmin")
	public String unAssignedRolesToAdmin(Integer adminId, @RequestParam("ids")List<Integer> ids) {
		adminService.unAssignedRolesToAdmin(adminId, ids);
		return "ok";
	}
	
	// 11.给用户分配角色
	@ResponseBody
	@PostMapping("/assignedRolesToAdmin")
	public String assignedRolesToAdmin(Integer adminId, @RequestParam("ids")List<Integer> ids) {
		logger.debug("传入的ids是：{}", ids);
		adminService.assignedRolesToAdmin(adminId, ids);
		return "ok";
	}
	
	// 10.转发到分配角色权限的方法
	@GetMapping("/assignedRolePage")
	public String assignedRolePage(Integer id, Model model) {
		//查询所有的角色
		List<TRole> roles = roleService.getRoles(null);
		//查询管理员已分配的角色id集合
		List<Integer> roleIds = roleService.getRoleIdsByAdminId(id);
		//将数据筛选为已分配和未分配的角色集合
		List<TRole> assignedRoles = new ArrayList<TRole>();
		List<TRole> unAssignedRoles = new ArrayList<TRole>();
		for (TRole role : roles) {
			if (roleIds.contains(role.getId())) {
				assignedRoles.add(role);
			}else {
				unAssignedRoles.add(role);
			}
		}
		//将数据保存在域中
		model.addAttribute("assignedRoles", assignedRoles);
		model.addAttribute("unAssignedRoles", unAssignedRoles);
		//转发页面
		return "admin/assignedRole";
	}
	
	// 9.更新管理员信息
	@ResponseBody
	@PostMapping("/updateAdmin")
	public String updateAdmin(TAdmin admin) {
		try {
			adminService.updateAdmin(admin);
			return "ok";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	// 8.根据id查询管理员信息
	@ResponseBody
	@GetMapping("/getAdmin")
	public TAdmin getAdmin(Integer id) {
		return adminService.getAdmin(id);
	}
	
	// 7.异步请求添加管理员
	@ResponseBody
	@PostMapping("/addAdmin")
	public String addAdmin(TAdmin admin, Model model) {
		try {
			adminService.addAdmin(admin);
			//将一个最大数存到session中，用于最后一页的跳转
			model.addAttribute("pages", Integer.MAX_VALUE);
			return "ok";
		} catch (Exception e) {			
			return e.getMessage();
		}
	}
	
	// 6.根据id数组批量删除管理员
	@GetMapping("/batchDelAdmins")
	public String batchDelAdmins(@RequestParam(value = "id")List<Integer> id, @RequestHeader(value = "referer")String referer) {
		adminService.batchDelAdmins(id);
		return "redirect:" + referer;
	}
	
	// 5.根据指定的id删除管理员
	@PreAuthorize("hasAnyRole('PG - 程序员')")
	@GetMapping("/deleteAdmin")
	public String deleteAdminById(Integer id, @RequestHeader(value = "referer")String referer) {
		adminService.deleteAdminById(id);
		//删除成功后需要跳转到当前页（或者上一页），可以根据请求头来跳回原来页面
		return "redirect:" + referer;
	}

	// 4.转发用户维护，并分页显示管理员列表，如果有查询条件condition，就采用条件查询
	@GetMapping("/user.html")
	public String toUserPage(@RequestParam(required = false, defaultValue = "") String condition,
			@RequestParam(required = false, defaultValue = "1") Integer pageNum, Model model) {
		// 本次查询启动分页
		// pageNum：要查询分页的页码 pageSize：每页显示的记录条数
		PageHelper.startPage(pageNum, 5);
		List<TAdmin> admins = adminService.getAllAdmins(condition);
		// 可以使用pageHelper提供的page类，细化分页数据
		// 参数1：分页的数据集合 参数2：页面中需要显示的页码数量
		PageInfo<TAdmin> pageInfo = new PageInfo<>(admins, 3);
		
		model.addAttribute("pageInfo", pageInfo);
		return "admin/user";
	}

	// 3.退出登录
	/*@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("admin");
		return "index";
	}*/

	// 2.跳转到main页面的方法
	@GetMapping("/main.html")
	public String toMainPage(HttpSession session) {
		// 准备main页面需要的菜单集合
		List<TMenu> menuList = menuService.getPMenuList();
		session.setAttribute("menus", menuList);
		// 由于管理员的一次会话中多次请求都需要使用菜单集合，所以需要存到session域中
		// logger.info("查询菜单集合: {}", menuList);
		return "admin/main";
	}

	// 1.处理管理员的登录请求
	/*@PostMapping(value = "/doLogin")
	public String doLogin(TAdmin loginAdmin, HttpSession session, Model model) {
		// 调用业务层处理业务
		TAdmin admin = adminService.doLogin(loginAdmin);
		// 验证查询的用户是否存在
		if (admin == null) {

			String errorMsg = "账号或密码错误";
			model.addAttribute("errorMsg", errorMsg);
			model.addAttribute("loginAcct", loginAdmin.getLoginacct());
			return "admin/login";
		} else {
			// 根据返回值判断登录成功重定向到main.xml,并存入用户信息
			session.setAttribute("admin", admin);
			return "redirect:/admin/main.html";
		}
	}*/
}
