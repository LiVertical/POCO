package com.entities;

import java.util.Date;

/**
 * Notifiaction entity. @author MyEclipse Persistence Tools
 */

public class Notifiaction implements java.io.Serializable {

	// Fields

	private String notifiactionId;
	private String notifiactionTitle;
	private String notifiactionInfo;
	private String userId;
	private Date createTime;
	private Integer usefulLife;
	private String curStatus;
	private String notifiactionGroupId;
	private Date updateTime;
	private String updateUser;
	private String createUser;

	// Constructors

	/** default constructor */
	public Notifiaction() {
	}

	/** minimal constructor */
	public Notifiaction(String notifiactionInfo) {
		this.notifiactionInfo = notifiactionInfo;
	}

	/** full constructor */
	public Notifiaction(String notifiactionTitle, String notifiactionInfo,
			String userId, Date createTime, Integer usefulLife,
			String curStatus, String notifiactionGroupId, Date updateTime,
			String updateUser, String createUser) {
		this.notifiactionTitle = notifiactionTitle;
		this.notifiactionInfo = notifiactionInfo;
		this.userId = userId;
		this.createTime = createTime;
		this.usefulLife = usefulLife;
		this.curStatus = curStatus;
		this.notifiactionGroupId = notifiactionGroupId;
		this.updateTime = updateTime;
		this.updateUser = updateUser;
		this.createUser = createUser;
	}

	// Property accessors

	public String getNotifiactionId() {
		return this.notifiactionId;
	}

	public void setNotifiactionId(String notifiactionId) {
		this.notifiactionId = notifiactionId;
	}

	public String getNotifiactionTitle() {
		return this.notifiactionTitle;
	}

	public void setNotifiactionTitle(String notifiactionTitle) {
		this.notifiactionTitle = notifiactionTitle;
	}

	public String getNotifiactionInfo() {
		return this.notifiactionInfo;
	}

	public void setNotifiactionInfo(String notifiactionInfo) {
		this.notifiactionInfo = notifiactionInfo;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUsefulLife() {
		return this.usefulLife;
	}

	public void setUsefulLife(Integer usefulLife) {
		this.usefulLife = usefulLife;
	}

	public String getCurStatus() {
		return this.curStatus;
	}

	public void setCurStatus(String curStatus) {
		this.curStatus = curStatus;
	}

	public String getNotifiactionGroupId() {
		return this.notifiactionGroupId;
	}

	public void setNotifiactionGroupId(String notifiactionGroupId) {
		this.notifiactionGroupId = notifiactionGroupId;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

}