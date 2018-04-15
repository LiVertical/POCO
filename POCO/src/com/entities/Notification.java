package com.entities;

import java.util.Date;

/**
 * Notifiaction entity. @author MyEclipse Persistence Tools
 */

public class Notification implements java.io.Serializable {

	// Fields

	private String notificationId;
	private String notificationTitle;
	private String notificationInfo;
	private String userId;
	private Date createTime;
	private Integer usefulLife;
	private String curStatus;
	private String notificationGroupId;
	private Date updateTime;
	private String updateUser;
	private String createUserId;
	private String createUserName;

	// Constructors

	/** default constructor */
	public Notification() {
	}

	/** minimal constructor */
	public Notification(String notificationInfo) {
		this.notificationInfo = notificationInfo;
	}

	/** full constructor */

	// Property accessors

	public String getNotificationId() {
		return this.notificationId;
	}

	public Notification(String notificationId, String notificationTitle,
			String notificationInfo, String userId, Date createTime,
			Integer usefulLife, String curStatus, String notificationGroupId,
			Date updateTime, String updateUser, String createUserId,
			String createUserName) {
		super();
		this.notificationId = notificationId;
		this.notificationTitle = notificationTitle;
		this.notificationInfo = notificationInfo;
		this.userId = userId;
		this.createTime = createTime;
		this.usefulLife = usefulLife;
		this.curStatus = curStatus;
		this.notificationGroupId = notificationGroupId;
		this.updateTime = updateTime;
		this.updateUser = updateUser;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getNotificationTitle() {
		return notificationTitle;
	}

	public void setNotificationTitle(String notificationTitle) {
		this.notificationTitle = notificationTitle;
	}

	public String getNotificationInfo() {
		return notificationInfo;
	}

	public void setNotificationInfo(String notificationInfo) {
		this.notificationInfo = notificationInfo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUsefulLife() {
		return usefulLife;
	}

	public void setUsefulLife(Integer usefulLife) {
		this.usefulLife = usefulLife;
	}

	public String getCurStatus() {
		return curStatus;
	}

	public void setCurStatus(String curStatus) {
		this.curStatus = curStatus;
	}

	public String getNotificationGroupId() {
		return notificationGroupId;
	}

	public void setNotificationGroupId(String notificationGroupId) {
		this.notificationGroupId = notificationGroupId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}
}