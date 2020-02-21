package com.atcrowdfunding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atcrowdfunding.bean.TMenu;
import com.atcrowdfunding.service.MenuService;

@RequestMapping("/menu")
@Controller
public class MenuController {
	@Autowired
	MenuService menuService;
	
	// 6.修改菜单
	@ResponseBody
	@PostMapping("/updateMenu")
	public String updateMenu(TMenu menu) {
		menuService.updateMenu(menu);
		return "ok";
	}
	
	// 5.根据id查询菜单
	@ResponseBody
	@GetMapping("/getMenuById")
	public TMenu getMenuById(Integer id) {
		return menuService.getMenuById(id);
	}
	
	// 4.删除菜单
	@ResponseBody
	@GetMapping("/delMenu")
	public String delMenu(Integer id) {
		menuService.delMenu(id);
		return "ok";
	}
	
	// 3.添加菜单的请求
	@ResponseBody
	@PostMapping("/addMenu")
	public String addMenu(TMenu menu) {
		menuService.addMenu(menu);
		return "ok";
	}

	// 2.处理异步加载菜单集合的请求
	@PreAuthorize("hasAnyRole('PG - 程序员')")
	@ResponseBody
	@GetMapping("/getMenus")
	public List<TMenu> getMenus() {
		return menuService.getAllMenu();
	}
	
	// 1.转发到许可维护页面
	@GetMapping("/menu.html")
	public String toMenuPage() {
		return "menu/menu";
	}
}
