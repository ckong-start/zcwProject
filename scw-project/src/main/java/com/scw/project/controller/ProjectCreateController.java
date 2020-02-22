package com.scw.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.scw.common.bean.AppResponse;
import com.scw.common.consts.ProjectConstant;
import com.scw.common.utils.ScwAppUtils;
import com.scw.common.vo.BaseVo;
import com.scw.project.bean.TMember;
import com.scw.project.bean.TProjectInitiator;
import com.scw.project.bean.TReturn;
import com.scw.project.service.ProjectService;
import com.scw.project.utils.OSSTemplate;
import com.scw.project.vo.ProjectConfirmVo;
import com.scw.project.vo.ProjectRedisStorageVo;
import com.scw.project.vo.ProjectReturnVo;
import com.scw.project.vo.projectBaseInfoVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "项目发起模块")
@RestController
public class ProjectCreateController {

	@ApiOperation(value = "确认项目法人信息")
	@PostMapping("/project/create/confirm/legal")
	public String createConfirmLegal() {

		return null;
	}

	@Autowired
	StringRedisTemplate srt;

	@ApiOperation(value = "项目初始创建:阅读并同意协议")
	@PostMapping("/project/create/init")
	public AppResponse<Object> initProject(BaseVo vo) {
		// 判断提交的请求参数中accessToken是否为null
		if (StringUtils.isEmpty(vo.getAccessToken())) {
			return AppResponse.fail("10030", "无权操作，请登录");
		}
		// ①验证用户是否登录：获取调用者提交的accessToken，根据token从redis中获取member对象，获取到代表已经登录
		String memberKey = "login:" + vo.getAccessToken() + ":token";
		if (!srt.hasKey(memberKey)) {
			return AppResponse.fail("10031", "登录超时，请重新登录");
		}
		// 使用common工程的工具类从redis中获取json转为对象
		TMember member = ScwAppUtils.jsonStr2Obj(TMember.class, memberKey, srt);
		// ②初始化BigVo对象，并初始化唯一的projecttoken键：projectToken = "project:create:temp:"+uuid
		ProjectRedisStorageVo bigVo = new ProjectRedisStorageVo();
		bigVo.setAccessToken(vo.getAccessToken());
		bigVo.setMemberid(member.getId());
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String projectToken = ProjectConstant.PROJECT_CREATE_PREFIX + uuid;
		bigVo.setProjectToken(projectToken);

		// ③将BigVo对象装为json字符串存到redis中，使用common的工具类将对象存入redis中
		ScwAppUtils.saveObj2Redis(bigVo, projectToken, srt);
		// ④响应projecttoken给调用者
		return AppResponse.ok(bigVo);
	}

	@ApiOperation(value = "项目发布第一步：项目及发起人信息")
	@PostMapping("/project/create/step1")
	public AppResponse<Object> step1(projectBaseInfoVo vo) {
		// 1.判断用户是否登录
		String memberKey = "login:" + vo.getAccessToken() + ":token";
		if (!srt.hasKey(memberKey)) {
			return AppResponse.fail("10031", "登录超时，请重新登录");
		}
		// 2.获取提交参数的projecttoken，从redis中获取bigVo对象
		ProjectRedisStorageVo bigVo = ScwAppUtils.jsonStr2Obj(ProjectRedisStorageVo.class, vo.getProjectToken(), srt);
		// 3.将本次收集到的数据设置给bigVo对象
		BeanUtils.copyProperties(vo, bigVo);
		// 4.使用那个projectToken将接受了新数据的bigVo对象设置到redis中覆盖上一步的bigVo
		ScwAppUtils.saveObj2Redis(bigVo, vo.getProjectToken(), srt);
		// 5.给调用者响应
		return AppResponse.ok(bigVo);
	}

	@ApiOperation(value = "项目发布第二步：收集回报信息")
	@PostMapping("/project/create/step2")
	public AppResponse<Object> step2(@RequestBody List<ProjectReturnVo> vos) {
		if (CollectionUtils.isEmpty(vos)) {
			return AppResponse.fail("10050", "没有获取到回报集合");
		}
		// 1.验证登录：accessToken
		ProjectReturnVo returnVo = vos.get(0);
		String memberKey = "login:" + returnVo.getAccessToken() + ":token";
		if (!srt.hasKey(memberKey)) {
			return AppResponse.fail("10031", "登录超时，请重新登录");
		}
		// 2.获取redis中的bigVo
		String projectToken = returnVo.getProjectToken();
		ProjectRedisStorageVo bigVo = ScwAppUtils.jsonStr2Obj(ProjectRedisStorageVo.class, projectToken, srt);
		// 3.将回报对象的集合设置给bigVo的List<TReturn>对象，此处需要new一个新的list集合用来添加TReturn对象，再将此list集合set给bigVo
		List<TReturn> rtnList = new ArrayList<>(vos.size());
		for (ProjectReturnVo projectReturnVo : vos) {
			TReturn rtn = new TReturn();
			BeanUtils.copyProperties(projectReturnVo, rtn);
			rtnList.add(rtn);
		}
		bigVo.setProjectReturns(rtnList);
		// 4.将bigVo存到redis中
		ScwAppUtils.saveObj2Redis(bigVo, projectToken, srt);
		// 5.响应数据
		return AppResponse.ok(bigVo);
	}

	@Autowired
	ProjectService projectService;
	
	@ApiOperation(value = "项目发布第三步：收集确认信息")
	@PostMapping("/project/create/step3")
	public AppResponse<Object> step3(ProjectConfirmVo vo) {
		//封装ProjectConfirmVo继承BaseVo，有四个字段：confirmType（项目提交的方式：0--草稿，1--发布）、
		//projectToken、account（收款账号)、idcard（法人身份证号码）
		// 1.验证登录
		String memberKey = "login:" + vo.getAccessToken() + ":token";
		if (!srt.hasKey(memberKey)) {
			return AppResponse.fail("10031", "登录超时，请重新登录");
		}
		// 2.获取redis中的bigVo对象
		ProjectRedisStorageVo bigVo = ScwAppUtils.jsonStr2Obj(ProjectRedisStorageVo.class, vo.getProjectToken(), srt);
		// 3.将本次获取到的参数更新到bigVo中
		TProjectInitiator initiator = new TProjectInitiator();
		initiator.setAccount(vo.getAccout());
		initiator.setIdcard(vo.getIdcard());
		bigVo.setProjectInitiator(initiator);
		
		// 4.将bigVo持久化，把bigVo中的数据拆分到数据库对应的表中
		if (vo.getConfirmType() == 1) {
			//正式持久化保存
			projectService.createProject(bigVo);
			//删除redis中bigVo的缓存
			srt.delete(vo.getProjectToken());
		}else {
			//草稿
		}
		// 5.响应结果
		return AppResponse.ok("项目发布成功");
	}

	@ApiOperation(value = "添加项目回报档位")
	@PostMapping("/project/create/return")
	public String createReturn() {

		return null;
	}

	@ApiOperation(value = "删除项目回报档位")
	@DeleteMapping("/project/create/return")
	public String delReturn() {

		return null;
	}

	@ApiOperation(value = "项目提交审核申请")
	@PostMapping("/project/create/submit")
	public String createSubmit() {

		return null;
	}

	@ApiOperation(value = "项目草稿保存")
	@PostMapping("/project/create/temsave")
	public String createTemSave() {

		return null;
	}

	@Autowired
	OSSTemplate ossTemplate;

	@ApiOperation(value = "上传图片")
	@PostMapping("/project/create/upload")
	public AppResponse<Object> uploadImgs(MultipartFile[] files) {
		if (ArrayUtils.isEmpty(files)) {
			return AppResponse.fail("100020", "没有获取到上传的文件");
		}
		List<String> filePaths = new ArrayList<>();
		for (MultipartFile file : files) {
			String filePath = ossTemplate.uploadFile(file);
			filePaths.add(filePath);
		}
		// 文件上传成功返回保存图片的url地址
		return AppResponse.ok(filePaths);
	}
}
