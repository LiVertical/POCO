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
	private Integer curStatus;
	private Integer	auditStatus;// 1:通过审核，0：待审核；-1：未通过审核
	private String activityGroupId;
	private Date applyTime; //申请时间


	public Activities(String activityId, String activityInfo,
			String activityName, String activityDesc, Date createTime,
			Date endTime, String userId, Integer curStatus,
			Integer auditStatus, String activityGroupId, Date applyTime) {
		super();
		this.activityId = activityId;
		this.activityInfo = activityInfo;
		this.activityName = activityName;
		this.activityDesc = activityDesc;
		this.createTime = createTime;
		this.endTime = endTime;
		this.userId = userId;
		this.curStatus = curStatus;
		this.auditStatus = auditStatus;
		this.activityGroupId = activityGroupId;
		this.applyTime = applyTime;
	}

	public Activities() {
		super();
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getActivityInfo() {
		return activityInfo;
	}

	public void setActivityInfo(String activityInfo) {
		this.activityInfo = activityInfo;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityDesc() {
		return activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getCurStatus() {
		return curStatus;
	}

	public void setCurStatus(Integer curStatus) {
		this.curStatus = curStatus;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getActivityGroupId() {
		return activityGroupId;
	}

	public void setActivityGroupId(String activityGroupId) {
		this.activityGroupId = activityGroupId;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}


}