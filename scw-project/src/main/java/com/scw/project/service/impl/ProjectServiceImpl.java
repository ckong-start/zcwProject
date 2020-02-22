package com.scw.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.scw.common.utils.AppDateUtils;
import com.scw.project.bean.TProject;
import com.scw.project.bean.TProjectImages;
import com.scw.project.bean.TProjectImagesExample;
import com.scw.project.bean.TProjectInitiator;
import com.scw.project.bean.TProjectInitiatorExample;
import com.scw.project.bean.TProjectTag;
import com.scw.project.bean.TProjectType;
import com.scw.project.bean.TReturn;
import com.scw.project.bean.TReturnExample;
import com.scw.project.mapper.TProjectImagesMapper;
import com.scw.project.mapper.TProjectInitiatorMapper;
import com.scw.project.mapper.TProjectMapper;
import com.scw.project.mapper.TProjectTagMapper;
import com.scw.project.mapper.TProjectTypeMapper;
import com.scw.project.mapper.TReturnMapper;
import com.scw.project.service.ProjectService;
import com.scw.project.vo.ProjectDetailsVo;
import com.scw.project.vo.ProjectRedisStorageVo;
import com.scw.project.vo.ProjectVo;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	TProjectMapper projectMapper;
	@Autowired
	TProjectImagesMapper projectImgMapper;
	@Autowired
	TProjectInitiatorMapper projectInitiatorMapper;
	@Autowired
	TReturnMapper returnMapper;
	@Autowired
	TProjectTagMapper projectTagMapper;
	@Autowired
	TProjectTypeMapper projectTypeMapper;
	
	// 将项目持久化
	@Override
	public void createProject(ProjectRedisStorageVo bigVo) {
		// ①project--Tproject：其他属性可以对拷，但有几个需要手动指定，如money的数据类型不一致；
		// status和createDate字段需要后台生成默认值。然后将project对象保存到数据库中，
		// 另外后面的表需要用到projectid，所以需要到mapper中返回id值（userGenerateKey=true，keyProperty=“id”）
		TProject project = new TProject();
		BeanUtils.copyProperties(bigVo, project);
		project.setMoney((long)bigVo.getMoney());
		int status = 0;//未审核
		project.setStatus("" + status);
		project.setCreatedate(AppDateUtils.getFormatTime());
		projectMapper.insertSelective(project);
		Integer projectid = project.getId();
		// ②project_images--TProjectImages：new一个List<TProjectImages>集合，
		// 先设置头图（new TProjectImages()）设置type为0表示头图；再遍历详情图，分别设置type于1；
		// 将TProjectImages对象添加到List集合中；在projectImgMapper中新增批量插入图片对象到数据库中
		// （batchInsertProjectImgs(List<TProjectImages> imgs)）
		List<TProjectImages> imgList = new ArrayList<TProjectImages>();
		TProjectImages headImg = new TProjectImages();
		headImg.setImgurl(bigVo.getHeaderImage());//设置头图
		headImg.setImgtype((byte)0);
		headImg.setProjectid(projectid);
		imgList.add(headImg);
		//设置详细图
		for (String detailsImageUrl : bigVo.getDetailsImage()) {
			TProjectImages image = new TProjectImages();
			image.setImgurl(detailsImageUrl);
			image.setImgtype((byte)1);
			image.setProjectid(projectid);
			imgList.add(image);
		}
		//调用mapper保存img到数据库中
		projectImgMapper.batchInsertImg(imgList);
		
		// ③project_initiator--
		TProjectInitiator initiator = bigVo.getProjectInitiator();
		initiator.setProjectid(projectid);
		projectInitiatorMapper.insertSelective(initiator);
		
		// ④project_tag
		// ⑤project_type--④和⑤类似，同样是遍历属性存入到list中，然后新增批量插入的mapper方法
		List<TProjectTag> tagList = new ArrayList<>();
		for (Integer tagId : bigVo.getTagids()) {
			TProjectTag projectTag = new TProjectTag();
			projectTag.setTagid(tagId);
			projectTag.setProjectid(projectid);
			tagList.add(projectTag);
		}
		projectTagMapper.batchInsertTags(tagList);
		List<TProjectType> typeList = new ArrayList<>();
		for (Integer typeId : bigVo.getTypeids()) {
			TProjectType projectType = new TProjectType();
			projectType.setTypeid(typeId);
			projectType.setProjectid(projectid);
			typeList.add(projectType);
		}
		projectTypeMapper.batchInsertType(typeList);
		
		
		// ⑥return--同样需要批量插入return的list集合
		List<TReturn> rtnList = bigVo.getProjectReturns();
		for (TReturn tReturn : rtnList) {
			tReturn.setProjectid(projectid);
		}
		returnMapper.batchInsertRtns(rtnList);
	}

	@Override
	public List<ProjectVo> getProjectVos() {
		//两表联查
		List<ProjectVo> projectVos = projectMapper.selectProjectVosAndHeaderImg();
		return projectVos;
	}

	@Override
	public ProjectDetailsVo getInfoDetail(Integer id) {
		ProjectDetailsVo vo = new ProjectDetailsVo();
		//查询project信息
		TProject project = projectMapper.selectByPrimaryKey(id);
		if (project != null) {
			BeanUtils.copyProperties(project, vo);
		}
		
		//查询项目发起人的信息
		TProjectInitiatorExample example = new TProjectInitiatorExample();
		example.createCriteria().andProjectidEqualTo(id);
		List<TProjectInitiator> list = projectInitiatorMapper.selectByExample(example);
		if (!CollectionUtils.isEmpty(list)) {
			TProjectInitiator initiator = list.get(0);
			BeanUtils.copyProperties(initiator, vo);
		}
		
		//查询项目的回报信息
		TReturnExample rtnExample = new TReturnExample();
		rtnExample.createCriteria().andProjectidEqualTo(id);
		List<TReturn> rtnsList = returnMapper.selectByExample(rtnExample);
		if (!CollectionUtils.isEmpty(rtnsList)) {
			vo.setRtns(rtnsList);
		}
		
		//查询项目图片信息
		TProjectImagesExample imgExample = new TProjectImagesExample();
		imgExample.createCriteria().andProjectidEqualTo(id);
		List<TProjectImages> imgList = projectImgMapper.selectByExample(imgExample);
		if (!CollectionUtils.isEmpty(imgList)) {
			List<String> detailsUrl = new ArrayList<>();
			//遍历图片集合，判断是头图还是详情图
			for (TProjectImages img : imgList) {
				if (img.getImgtype() == 0) {
					//头图
					vo.setHeaderImgurl(img.getImgurl());
				}else {
					//详情图
					detailsUrl.add(img.getImgurl());
				}
			}
			//详请图集合创建完毕
			vo.setDetailsImgurls(detailsUrl);
		}
		return vo;
	}

	@Override
	public TReturn getReturnInfo(Integer rtnid) {
		return returnMapper.selectByPrimaryKey(rtnid);
	}

}
