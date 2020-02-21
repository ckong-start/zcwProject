package com.atcrowdfunding.exception;

//管理员账号异常
public class AdminAccountException extends RuntimeException {

	
	private static final long serialVersionUID = -5284582808038676449L;

	public AdminAccountException(String msg) {
		super(msg);
	}
}
