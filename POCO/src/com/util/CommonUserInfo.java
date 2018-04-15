package com.util;

public class CommonUserInfo {

	private String userName;
	private String password;
	private String loginName;
	private String userId;
	private int role;
	
	public CommonUserInfo() {
		super();
	}

	public CommonUserInfo(String userName, String password, String loginName,
			String userId, int role) {
		super();
		this.userName = userName;
		this.password = password;
		this.loginName = loginName;
		this.userId = userId;
		this.role = role;
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
	
}
