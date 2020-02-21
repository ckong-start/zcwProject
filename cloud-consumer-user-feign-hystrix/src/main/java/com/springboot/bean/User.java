package com.springboot.bean;

public class User {

	private Integer id;
	private String UserName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", UserName=" + UserName + "]";
	}
	
}
