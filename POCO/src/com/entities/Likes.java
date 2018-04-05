package com.entities;

import java.util.Date;

/**
 * Likes entity. @author MyEclipse Persistence Tools
 */

public class Likes implements java.io.Serializable {

	// Fields

	private String userId;
	private String id;
	private String productId;
	private String productName;
	private String notifiactionId;
	private String notifiactionName;
	private Date createTime;

	// Constructors

	/** default constructor */
	public Likes() {
	}


	public Likes(String userId, String id, String productId,
			String productName, String notifiactionId, String notifiactionName,
			Date createTime) {
		super();
		this.userId = userId;
		this.id = id;
		this.productId = productId;
		this.productName = productName;
		this.notifiactionId = notifiactionId;
		this.notifiactionName = notifiactionName;
		this.createTime = createTime;
	}

	// Property accessors

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getNotifiactionId() {
		return this.notifiactionId;
	}

	public void setNotifiactionId(String notifiactionId) {
		this.notifiactionId = notifiactionId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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