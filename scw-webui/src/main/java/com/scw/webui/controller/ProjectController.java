package com.scw.webui.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scw.common.bean.AppResponse;
import com.scw.common.consts.ProjectConstant;
import com.scw.webui.bean.TReturn;
import com.scw.webui.service.ProjectServiceFeignClient;
import com.scw.webui.vo.ProjectDetailsVo;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/project")
@Slf4j
public class ProjectController {
	
	@Autowired
	ProjectServiceFeignClient projectServiceFeignClient;

	//1.转发项目详情的方法
	@GetMapping("/project.html")
	public String getProjectInfo(Model model, Integer id, HttpSession session){
		//调用projectFeignClient远程调用    project项目查询id对应的项目详情
		AppResponse<ProjectDetailsVo> response = projectServiceFeignClient.getInfoDetail(id);
		//将项目项目详情对象存到request域中
		if (!"00000".equals(response.getCode())) {
			return "error/502";
		}
		model.addAttribute(ProjectConstant.PROJECT_DETAILS_KEY, response.getData());
		//共享数据到session域中
		session.setAttribute("projectDetailsVo", response.getData());
		log.debug("热门项目的数据：{}", response.getData());
		//转发到project详情显示页面
		return "project/project";
	}
	
	//2.点击支持按钮转发请求到payStep1页面
	@GetMapping("/support")
	public String toPayStep1(Integer projectid, Integer rtnid, HttpSession session) {
//		1.从查询项目详情方法中session域中共享的数据内查询项目名称、发起人信息，其他方法中已经存过，此处不用实现
//		2.查询回报内容，使用远程调用，（在project工程中添加根据rtnid查询回报信息的方法）
		AppResponse<TReturn> response = projectServiceFeignClient.getReturnInfo(rtnid);
		TReturn tReturn = response.getData();
		log.info("查询到id={}的回报的信息为：{}", rtnid , tReturn);
//		3.将数据存到session域中
		session.setAttribute("rtn", tReturn);
//		4.转发到pay-step-1页面
		return "order/pay-step-1";
	}
}
