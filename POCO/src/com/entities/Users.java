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
	private Integer age;
	private String email;
	private Integer sex;
	private String faceImg;
	private Integer role;
	private Date createTime;
	private String curStatus;

		/** full constructor */
public Users(String userId, String userName, String loginName,
			String loginPass, Integer age, String email, Integer sex,
			String faceImg, Integer role, Date createTime, String curStatus) {
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
		this.curStatus = curStatus;
	}

		public Users() {
			super();
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

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Integer getSex() {
			return sex;
		}

		public void setSex(Integer sex) {
			this.sex = sex;
		}

		public String getFaceImg() {
			return faceImg;
		}

		public void setFaceImg(String faceImg) {
			this.faceImg = faceImg;
		}

		public Integer getRole() {
			return role;
		}

		public void setRole(Integer role) {
			this.role = role;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public String getCurStatus() {
			return curStatus;
		}

		public void setCurStatus(String curStatus) {
			this.curStatus = curStatus;
		}

}