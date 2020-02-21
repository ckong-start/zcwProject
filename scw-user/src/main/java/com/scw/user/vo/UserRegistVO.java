package com.scw.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@ApiModel("用户注册的VO类")
public class UserRegistVO {
	@ApiModelProperty("注册的手机号码")
	private String loginacct;
	@ApiModelProperty("用户密码")
	private String userpswd;
	@ApiModelProperty("用户邮箱")
	private String email;
	@ApiModelProperty("用户类型：0-个人，1-企业用户")
	private String usertype;
	@ApiModelProperty("手机验证码")
	private String code;
	
}
