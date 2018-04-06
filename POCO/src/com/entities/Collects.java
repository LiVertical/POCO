package com.entities;

import java.util.Date;

/**
 * Collects entity. @author MyEclipse Persistence Tools
 */

public class Collects implements java.io.Serializable {

	private String id;
	private String userId;
	private String productId;
	private String productName;
	private Date createTime;
	
	
	
	public Collects() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Collects(String id, String userId, String productId,
			String productName, Date createTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.productName = productName;
		this.createTime = createTime;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	

}