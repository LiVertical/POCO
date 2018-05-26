package com.entities;

import java.util.Date;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */

public class Users implements java.io.Serializable {

	// Fields

	private String userId;
	private String userName;
	private String loginName;
	private String loginPass;
	private int age;
	private String email;
	private int sex;
	private String faceImg;
	private int role;
	private Date createTime;
	private Date updateTime;

		/** full constructor */
	

		public Users() {
			super();
		}

		public Users(String userId, String userName, String loginName,
				String loginPass, int age, String email, int sex,
				String faceImg, int role, Date createTime, Date updateTime) {
			super();
			this.userId = userId;
			this.userName = userName;
			this.loginName = loginName;
			this.loginPass = loginPass;
			this.age = age;
			this.email = email;
			this.sex = sex;
			this.faceImg = faceImg;
			this.role = role;
			this.createTime = createTime;
			this.updateTime = updateTime;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getLoginName() {
			return loginName;
		}

		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}

		public String getLoginPass() {
			return loginPass;
		}

		public void setLoginPass(String loginPass) {
			this.loginPass = loginPass;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public int getSex() {
			return sex;
		}

		public void setSex(int sex) {
			this.sex = sex;
		}

		public String getFaceImg() {
			return faceImg;
		}

		public void setFaceImg(String faceImg) {
			this.faceImg = faceImg;
		}

		public int getRole() {
			return role;
		}

		public void setRole(int role) {
			this.role = role;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		
		public Date getUpdateTime() {
			return updateTime;
		}
		
		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}
}