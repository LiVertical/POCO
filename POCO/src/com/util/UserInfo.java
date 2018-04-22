package com.util;

import java.io.Serializable;

public class UserInfo implements Serializable{

	private String userName;
	private String password;
	private String loginName;
	private String userId;
	private int role;
	private String userImg;
	
	public UserInfo() {
		super();
	}

	public UserInfo(String userName, String password, String loginName,
			String userId, int role,String userImg) {
		super();
		this.userName = userName;
		this.password = password;
		this.loginName = loginName;
		this.userId = userId;
		this.role = role;
		this.userImg = userImg;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	
}
