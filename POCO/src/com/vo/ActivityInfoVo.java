package com.vo;

import java.util.Date;

public class ActivityInfoVo {
	private String activityId;
	private String activityName;
	private String activityDesc;
	private String ActivityUser;
	private Date StartTime;
	private Date applyTime;
	private Date endTime;
	private int auditStatus;
	private int curStatus;
	
	public ActivityInfoVo() {
		super();
	}

	public ActivityInfoVo(String activityId, String activityName,
			String activityDesc, String activityUser, Date startTime,
			Date applyTime, Date endTime, int auditStatus, int curStatus) {
		super();
		this.activityId = activityId;
		this.activityName = activityName;
		this.activityDesc = activityDesc;
		ActivityUser = activityUser;
		StartTime = startTime;
		this.applyTime = applyTime;
		this.endTime = endTime;
		this.auditStatus = auditStatus;
		this.curStatus = curStatus;
	}


	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getActivityDesc() {
		return activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}

	public String getActivityUser() {
		return ActivityUser;
	}

	public void setActivityUser(String activityUser) {
		ActivityUser = activityUser;
	}

	public Date getStartTime() {
		return StartTime;
	}

	public void setStartTime(Date startTime) {
		StartTime = startTime;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}

	public int getCurStatus() {
		return curStatus;
	}

	public void setCurStatus(int curStatus) {
		this.curStatus = curStatus;
	}
	
}