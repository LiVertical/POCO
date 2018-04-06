package com.entities;

import java.util.Date;

/**
 * Comments entity. @author MyEclipse Persistence Tools
 */

public class Comments implements java.io.Serializable {

	// Fields

	private String commentId;
	private String commentUser;
	private String productId;
	private Date createTime;
	private String commentDesc;
	private int commentStatus;
	
	
	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comments(String commentId, String commentUser, String productId,
			Date createTime, String commentDesc, int commentStatus) {
		super();
		this.commentId = commentId;
		this.commentUser = commentUser;
		this.productId = productId;
		this.createTime = createTime;
		this.commentDesc = commentDesc;
		this.commentStatus = commentStatus;
	}
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getCommentUser() {
		return commentUser;
	}
	public void setCommentUser(String commentUser) {
		this.commentUser = commentUser;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCommentDesc() {
		return commentDesc;
	}
	public void setCommentDesc(String commentDesc) {
		this.commentDesc = commentDesc;
	}
	public int getCommentStatus() {
		return commentStatus;
	}
	public void setCommentStatus(int commentStatus) {
		this.commentStatus = commentStatus;
	}
	
}