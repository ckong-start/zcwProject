package com.atcrowdfunding.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atcrowdfunding.bean.TMenu;
import com.atcrowdfunding.mapper.TMenuMapper;
import com.atcrowdfunding.service.MenuService;
@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	TMenuMapper menuMapper;
	
	@Override
	public List<TMenu> getAllMenu(){
		return menuMapper.selectByExample(null);
	}
	
	@Override
	public List<TMenu> getPMenuList() {
		// 1- 查询所有的菜单
		List<TMenu> menus = getAllMenu();
		// 2- 将菜单组装为父子关系的集合[在TMenu类中添加成员属性 children存储子菜单集合]
		// 2.1 挑选出所有的父菜单(pid=0)
		Map<String, TMenu> menusMap = new HashMap<String, TMenu>();
		for (TMenu tMenu : menus) {
			if (tMenu.getPid() == 0) {
				// 使用父菜单的id作为键，父菜单作为值
				menusMap.put(tMenu.getId() + "", tMenu);
			}
		}
		// 2.2遍历所有菜单，如果正在遍历的菜单的pid=父菜单集合中的菜单的id
		for (TMenu tMenu : menus) {
			// 根据正在遍历菜单的pid 去pMenus中查找父菜单对象
			if (tMenu.getPid() != 0) {
				menusMap.get(tMenu.getPid() + "").getChildren().add(tMenu);
			}
		}
		
		return new ArrayList<TMenu>(menusMap.values());
	}

	@Override
	public void addMenu(TMenu menu) {
		menuMapper.insertSelective(menu);
		
	}

	@Override
	public void delMenu(Integer id) {
		menuMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public TMenu getMenuById(Integer id) {
		return menuMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateMenu(TMenu menu) {
		menuMapper.updateByPrimaryKeySelective(menu);
		
	}

}
