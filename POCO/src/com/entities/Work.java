package com.entities;

import java.util.Date;

/**
 * Work entity. @author MyEclipse Persistence Tools
 */

public class Work implements java.io.Serializable {

	// Fields

	private String workId;
	private String workName;
	private Date workUploadTime;
	private String workComment;
	private String workPath;
	private String productId;
	private String activityId;

	// Constructors

	/** default constructor */
	public Work() {
	}

	/** full constructor */
	public Work(String workName, Date workUploadTime, String workComment,
			String workPath, String productId,String activityId) {
		this.workName = workName;
		this.workUploadTime = workUploadTime;
		this.workComment = workComment;
		this.workPath = workPath;
		this.productId = productId;
		this.activityId = activityId;
	}

	// Property accessors

	public String getWorkId() {
		return this.workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getWorkName() {
		return this.workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public Date getWorkUploadTime() {
		return this.workUploadTime;
	}

	public void setWorkUploadTime(Date workUploadTime) {
		this.workUploadTime = workUploadTime;
	}

	public String getWorkComment() {
		return this.workComment;
	}

	public void setWorkComment(String workComment) {
		this.workComment = workComment;
	}

	public String getWorkPath() {
		return this.workPath;
	}

	public void setWorkPath(String workPath) {
		this.workPath = workPath;
	}

	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

}