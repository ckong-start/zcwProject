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
@ApiModel(value="登录成功的响应类")
public class UserRespVO {
	//创建VO对象：UserResVO类-登录成功的响应类（accessToken-访问令牌，以后的请求必须携带此令牌代表登录状态、loginacct、username、email、authstatus、usertype、realname、cardnum、acctype）
	@ApiModelProperty("访问令牌，以后的请求必须携带此令牌代表登录状态")
	private String accessToken;
	@ApiModelProperty("用户账号，注册的手机号码")
	private String loginacct;
	@ApiModelProperty("用户名")
	private String username;
	@ApiModelProperty("用户邮箱")
	private String email;
	@ApiModelProperty("认证状态:0未认证  1-认证")
	private String authstatus;
	@ApiModelProperty("用户类型：0-个人")
	private String usertype;
	@ApiModelProperty("真实姓名")
	private String realname;
	@ApiModelProperty("卡号")
	private String cardnum;
	@ApiModelProperty("账号类型")
	private String acctype;
	
	
}
