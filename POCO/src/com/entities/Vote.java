package com.entities;

import java.util.Date;

/**
 * Vote entity. @author MyEclipse Persistence Tools
 */

public class Vote implements java.io.Serializable {

	// Fields
	private String voteId;
	private String userId;
	private String workId;
	private Date createTime;
	
	public Vote() {
		super();
	}
	
	public Vote(String voteId, String userId, String workId, Date createTime) {
		super();
		this.voteId = voteId;
		this.userId = userId;
		this.workId = workId;
		this.createTime = createTime;
	}
	
	public String getVoteId() {
		return voteId;
	}
	public void setVoteId(String voteId) {
		this.voteId = voteId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}