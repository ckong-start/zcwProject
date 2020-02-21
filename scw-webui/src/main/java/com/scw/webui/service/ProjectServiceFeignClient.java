package com.scw.webui.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scw.common.bean.AppResponse;
import com.scw.webui.bean.TReturn;
import com.scw.webui.service.impl.ProjectServiceFeignClientExceptionHandler;
import com.scw.webui.vo.ProjectDetailsVo;
import com.scw.webui.vo.ProjectVo;

@FeignClient(value="SCW-PROJECT", fallback=ProjectServiceFeignClientExceptionHandler.class)
public interface ProjectServiceFeignClient {
	
	@GetMapping("/project/recommend/index")
	public AppResponse<List<ProjectVo>> index();
	
	//查询指定项目详情的方法
	@GetMapping("/project/info/detail")
	public AppResponse<ProjectDetailsVo> getInfoDetail(@RequestParam("id")Integer id);

	@GetMapping("/project/return/info")
	public AppResponse<TReturn> getReturnInfo(@RequestParam("rtnid")Integer rtnid);
}
