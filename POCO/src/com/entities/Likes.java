package com.entities;

import java.util.Date;

/**
 * Likes entity. @author MyEclipse Persistence Tools
 */

public class Likes implements java.io.Serializable {

	// Fields
	private String id;
	private String userId;
	private String productId;
	private String productName;
	private String notifiactionId;
	private String notifiactionName;
	private Date createTime;
	
	public Likes(String id, String userId, String productId,
			String productName, String notifiactionId, String notifiactionName,
			Date createTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.productName = productName;
		this.notifiactionId = notifiactionId;
		this.notifiactionName = notifiactionName;
		this.createTime = createTime;
	}

	public Likes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getNotifiactionId() {
		return notifiactionId;
	}

	public void setNotifiactionId(String notifiactionId) {
		this.notifiactionId = notifiactionId;
	}

	public String getNotifiactionName() {
		return notifiactionName;
	}

	public void setNotifiactionName(String notifiactionName) {
		this.notifiactionName = notifiactionName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}