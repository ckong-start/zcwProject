package com.scw.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppResponse<T> {

	private String code;//响应状态码：  响应成功还是失败  "00000"代表成功 ，其他的都代表失败
	private String message;//响应描述字符串
	private T data;//响应数据： 用户查询、用户集合的查询、项目详情查询、项目列表查询
	
	//业务bean：成功的响应、失败的响应
	public static <T> AppResponse<T> ok(T data){
		return new AppResponse<T>("00000", "success", data);
	}
	public static <T> AppResponse<T> fail(String code, String message){
		return new AppResponse<T>(code, message, null);
	}
}
