package com.scw.project.mapper;

import com.scw.project.bean.TProject;
import com.scw.project.bean.TProjectExample;
import com.scw.project.vo.ProjectVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TProjectMapper {
    long countByExample(TProjectExample example);

    int deleteByExample(TProjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TProject record);

    int insertSelective(TProject record);

    List<TProject> selectByExample(TProjectExample example);

    TProject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TProject record, @Param("example") TProjectExample example);

    int updateByExample(@Param("record") TProject record, @Param("example") TProjectExample example);

    int updateByPrimaryKeySelective(TProject record);

    int updateByPrimaryKey(TProject record);
    //获取热门项目所需要的信息
	List<ProjectVo> selectProjectVosAndHeaderImg();
}