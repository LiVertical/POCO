package com.entities;

import java.util.Date;

public class Contests implements java.io.Serializable {

	private String contestId;
	private String contestDesc;
	private String contestName;
	private Date createTime;
	private Date startTime;
	private Date endTime;
	private String userId;
	private String userName;
	private int postStatus;
	private String contestGroupId;
	
	public Contests() {
		super();
	}
	
	public Contests(String contestId, String contestDesc, String contestName,
			Date createTime,Date startTime, Date endTime, String userId, String userName,
			int postStatus, String contestGroupId) {
		super();
		this.contestId = contestId;
		this.contestDesc = contestDesc;
		this.contestName = contestName;
		this.createTime = createTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.userId = userId;
		this.userName = userName;
		this.postStatus = postStatus;
		this.contestGroupId = contestGroupId;
	}
	public String getContestId() {
		return contestId;
	}
	public void setContestId(String contestId) {
		this.contestId = contestId;
	}
	public String getContestDesc() {
		return contestDesc;
	}
	public void setContestDesc(String contestDesc) {
		this.contestDesc = contestDesc;
	}
	public String getContestName() {
		return contestName;
	}
	public void setContestName(String contestName) {
		this.contestName = contestName;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getPostStatus() {
		return postStatus;
	}
	public void setPostStatus(int postStatus) {
		this.postStatus = postStatus;
	}
	public String getContestGroupId() {
		return contestGroupId;
	}
	public void setContestGroupId(String contestGroupId) {
		this.contestGroupId = contestGroupId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
}