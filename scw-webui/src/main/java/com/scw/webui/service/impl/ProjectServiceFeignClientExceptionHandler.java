package com.scw.webui.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.scw.common.bean.AppResponse;
import com.scw.webui.bean.TReturn;
import com.scw.webui.service.ProjectServiceFeignClient;
import com.scw.webui.vo.ProjectDetailsVo;
import com.scw.webui.vo.ProjectVo;
@Service
public class ProjectServiceFeignClientExceptionHandler implements ProjectServiceFeignClient {

	@Override
	public AppResponse<List<ProjectVo>> index() {
		
		return AppResponse.fail("30000", "服务器暂时关闭，请明天再试");
	}

	@Override
	public AppResponse<ProjectDetailsVo> getInfoDetail(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppResponse<TReturn> getReturnInfo(Integer rtnid) {
		// TODO Auto-generated method stub
		return null;
	}

}
