package com.entities;

import java.util.Date;

/**
 * Work entity. @author MyEclipse Persistence Tools
 */

public class Work implements java.io.Serializable {

	// Fields

	private Integer workId;
	private String workName;
	private Date workUploadTime;
	private String workComment;
	private String workPath;
	private Integer productId;

	// Constructors

	/** default constructor */
	public Work() {
	}

	/** full constructor */
	public Work(String workName, Date workUploadTime, String workComment,
			String workPath, Integer productId) {
		this.workName = workName;
		this.workUploadTime = workUploadTime;
		this.workComment = workComment;
		this.workPath = workPath;
		this.productId = productId;
	}

	// Property accessors

	public Integer getWorkId() {
		return this.workId;
	}

	public void setWorkId(Integer workId) {
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

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

}