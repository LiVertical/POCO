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

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** minimal constructor */
	public Users(String loginName, String loginPass, Integer role) {
		this.loginName = loginName;
		this.loginPass = loginPass;
		this.role = role;
	}

	/** full constructor */
	public Users(String userName, String loginName, String loginPass,
			Integer age, String email, Integer sex, String faceImg,
			Integer role, Date createTime, String curStatus) {
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

	// Property accessors

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPass() {
		return this.loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getFaceImg() {
		return this.faceImg;
	}

	public void setFaceImg(String faceImg) {
		this.faceImg = faceImg;
	}

	public Integer getRole() {
		return this.role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCurStatus() {
		return this.curStatus;
	}

	public void setCurStatus(String curStatus) {
		this.curStatus = curStatus;
	}

}