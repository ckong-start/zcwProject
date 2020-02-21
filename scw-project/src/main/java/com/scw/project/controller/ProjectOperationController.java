package com.scw.project.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="项目操作模块")
@RestController
public class ProjectOperationController {

	@ApiOperation(value="项目删除")
	@PutMapping("/project/operation/delete")
	public String delProject() {
		
		return null;
	}
	
	@ApiOperation(value="项目预览")
	@GetMapping("/project/operation/preshow")
	public String showProject() {
		
		return null;
	}
	
	@ApiOperation(value="项目问题查看")
	@GetMapping("/project/operation/question")
	public String showQusetion() {
		
		return null;
	}
	
	@ApiOperation(value="项目问题添加")
	@PostMapping("/project/operation/question")
	public String addQusetion() {
		
		return null;
	}
	
	@ApiOperation(value="项目问题答案添加")
	@PostMapping("/project/operation/question/answer")
	public String addQusetionAnswer() {
		
		return null;
	}
	
	@ApiOperation(value="项目关注")
	@PostMapping("/project/operation/star")
	public String addStarProject() {
		
		return null;
	}
	
	@ApiOperation(value="项目取消关注")
	@DeleteMapping("/project/operation/star")
	public String delStarProject() {
		
		return null;
	}
	
	@ApiOperation(value="项目修改")
	@PutMapping("/project/operation/update")
	public String updateProject() {
		
		return null;
	}
}
