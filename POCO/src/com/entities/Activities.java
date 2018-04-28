package com.entities;

import java.util.Date;

/**
 * Activities entity. @author MyEclipse Persistence Tools
 */

public class Activities implements java.io.Serializable {

	// Fields

	private String activityId;
	private String activityInfo;
	private String activityName;
	private String activityDesc;
	private Date createTime;
	private Date endTime;
	private String userId;
	private String userName;
	private Integer curStatus;
	private Integer	auditStatus;// 1:通过审核，0：待审核；-1：未通过审核
	private String activityGroupId;
	private String productId;
	private Date applyTime; //申请时间

	public Activities(String activityId, String activityInfo,
			String activityName, String activityDesc, Date createTime,
			Date endTime, String userId, String userName,Integer curStatus, Integer auditStatus,
			String activityGroupId, String productId,Date applyTime) {
		super();
		this.activityId = activityId;
		this.activityInfo = activityInfo;
		this.activityName = activityName;
		this.activityDesc = activityDesc;
		this.createTime = createTime;
		this.endTime = endTime;
		this.userId = userId;
		this.userName = userName;
		this.curStatus = curStatus;
		this.auditStatus = auditStatus;
		this.activityGroupId = activityGroupId;
		this.productId = productId;
		this.applyTime = applyTime;
	}

	public Activities() {
		super();
	}

	public String getActivityId() {
		return this.activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getActivityInfo() {
		return this.activityInfo;
	}

	public void setActivityInfo(String activityInfo) {
		this.activityInfo = activityInfo;
	}

	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityDesc() {
		return this.activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getCurStatus() {
		return this.curStatus;
	}

	public void setCurStatus(Integer curStatus) {
		this.curStatus = curStatus;
	}

	public String getActivityGroupId() {
		return this.activityGroupId;
	}

	public void setActivityGroupId(String activityGroupId) {
		this.activityGroupId = activityGroupId;
	}

	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}