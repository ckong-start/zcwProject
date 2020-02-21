package com.atcrowdfunding.service;

import java.util.List;

import com.atcrowdfunding.bean.TMenu;

public interface MenuService {
	//获取管理菜单
	List<TMenu> getAllMenu();
	//获取有子父类关系的管理菜单
	List<TMenu> getPMenuList();
	//添加菜单的功能
	void addMenu(TMenu menu);
	//根据id删除菜单
	void delMenu(Integer id);
	//根据id查询菜单
	TMenu getMenuById(Integer id);
	//修改菜单信息
	void updateMenu(TMenu menu);

}
