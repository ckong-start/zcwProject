package com.scw.project.service;

import java.util.List;

import com.scw.project.bean.TReturn;
import com.scw.project.vo.ProjectDetailsVo;
import com.scw.project.vo.ProjectRedisStorageVo;
import com.scw.project.vo.ProjectVo;

public interface ProjectService {

	void createProject(ProjectRedisStorageVo bigVo);
	//获取热门项目的方法
	List<ProjectVo> getProjectVos();
	//获取项目详情的方法
	ProjectDetailsVo getInfoDetail(Integer id);
	//根据回报id获取回报信息
	TReturn getReturnInfo(Integer rtnid);

}
