package com.scw.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scw.common.bean.AppResponse;
import com.scw.project.bean.TReturn;
import com.scw.project.service.ProjectService;
import com.scw.project.vo.ProjectDetailsVo;
import com.scw.project.vo.ProjectVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(tags="项目信息模块")
@Slf4j
public class ProjectInfoController {
	
	@ApiOperation(value="获取首页广告项目")
	@GetMapping("/project/adv")
	public String getAdv() {
		
		return null;
	}
	
	@ApiOperation(value="获取项目总览列表")
	@GetMapping("/project/all/index")
	public String getAllIndex() {
		
		return null;
	}
	
	@ApiOperation(value="获取项目详情信息")
	@GetMapping("/project/info/detail")
	public AppResponse<ProjectDetailsVo> getInfoDetail(@RequestParam("id")Integer id) {
		//调用业务层查询指定项目详情数据：t_return,t_project、t_project_initialtor、t_project_images
		//根据projectid去每个表中查询项目数据，然后再封装为projectDetailsVo对象
		ProjectDetailsVo vo = projectService.getInfoDetail(id);
		log.warn("查询id={} 的项目详情:{}", id , vo);
		return AppResponse.ok(vo);
	}
	
	@Autowired
	ProjectService projectService;
	
	@ApiOperation(value="获取首页热门推荐项目")
	@GetMapping("/project/recommend/index")
	public AppResponse<Object> index() {
		//首页显示热门项目数据时需要使用的数据：项目头图、项目名称name、项目简介remark、项目id(点击查询项目详情时使用)
		//1、封装reposnevo类描述需要相应的数据
		//2、调用project业务层处理查询热门项目集合的业务  并封装为ProjectVo对象的集合
		List<ProjectVo> projectVos = projectService.getProjectVos();
		log.debug("推荐项目：{}",projectVos);
		//3、给调用者响应
		return AppResponse.ok(projectVos);
	}
	
	@ApiOperation(value="获取首页分类推荐项目")
	@GetMapping("/project/recommend/type")
	public String getRecommendType() {
		
		return null;
	}
	
	@ApiOperation(value="获取项目回报档位信息")
	@GetMapping("/project/return/info")
	public AppResponse<TReturn> getReturnInfo(@RequestParam("rtnid")Integer rtnid) {
		TReturn rReturn = projectService.getReturnInfo(rtnid);
		
		return AppResponse.ok(rReturn);
	}
	
	@ApiOperation(value="获取项目系统标签信息")
	@GetMapping("/project/sys/tags")
	public String getSysTages() {
		
		return null;
	}
	
	@ApiOperation(value="获取项目系统分类信息")
	@GetMapping("/project/sys/type")
	public String getSysType() {
		
		return null;
	}
	
}
