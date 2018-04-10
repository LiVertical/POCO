package com.entities;

import java.util.Date;
/**
 * Activities entity. @author MyEclipse Persistence Tools
 */
public class Activities implements java.io.Serializable{
	
	private String activityId;
	private String activityInfo;
	private String activityName;
	private String activityDesc;
	private Date createTime;
	private Date endTime;
	private String userId;
	private int curStatus;
	private String activityGroupId;
	private String productId;
	private String productName;
	
	public Activities() {
		super();
	}

	public Activities(String activityId, String activityInfo,
			String activityName, Date createTime, Date endTime, String userId,
			int curStatus, String activityGroupId, String productId,String productName,String activityDesc) {
		super();
		this.activityId = activityId;
		this.activityInfo = activityInfo;
		this.activityName = activityName;
		this.activityDesc = activityDesc;
		this.createTime = createTime;
		this.endTime = endTime;
		this.userId = userId;
		this.curStatus = curStatus;
		this.activityGroupId = activityGroupId;
		this.productId = productId;
		this.productName = productName;
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
	public int getCurStatus() {
		return curStatus;
	}
	public void setCurStatus(int curStatus) {
		this.curStatus = curStatus;
	}
	public String getActivityGroupId() {
		return activityGroupId;
	}
	public void setActivityGroupId(String activityGroupId) {
		this.activityGroupId = activityGroupId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getActivityDesc() {
		return activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}
	
}
