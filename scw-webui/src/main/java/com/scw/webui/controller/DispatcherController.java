package com.scw.webui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.scw.common.bean.AppResponse;
import com.scw.webui.service.ProjectServiceFeignClient;
import com.scw.webui.vo.ProjectVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DispatcherController {
	@Autowired
	ProjectServiceFeignClient projectServiceFeignClient;

	//转发首页的方法
	@GetMapping(value= {"/" , "/index","/index.html"})
	public String toIndexPage(Model model) {
		//查询项目列表，存到域中
		AppResponse<List<ProjectVo>> response = projectServiceFeignClient.index();
		if (response == null) {
			return "index";
		}
		log.debug("获取到的response对象的数据为:{}",response.getData());
		model.addAttribute("projectVos", response.getData());
		//转发到首页获取遍历显示
		return "index";
	}
	
	//登录的方法--配置在config配置类中
	/*@GetMapping("/login.html")
	public String toLogin() {
		return "user/login";
	}*/
}
