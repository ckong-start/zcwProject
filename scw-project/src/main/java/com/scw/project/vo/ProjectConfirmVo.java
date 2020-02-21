package com.scw.project.vo;

import com.scw.common.vo.BaseVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("项目认证信息模块")
public class ProjectConfirmVo extends BaseVo{
	
	@ApiModelProperty("项目提交方式：0--草稿，1--发布")
	private Integer confirmType;
	@ApiModelProperty("项目临时的token")
	private String projectToken;
	@ApiModelProperty("收款账号")
	private String accout;
	@ApiModelProperty("法人身份证号码")
	private String idcard;
}
