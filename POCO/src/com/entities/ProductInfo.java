package com.entities;

import java.util.Date;

/**
 * Productinfo entity. @author MyEclipse Persistence Tools
 */

public class ProductInfo implements java.io.Serializable {

	// Fields

	private Integer productId;
	private String productName;
	private Date uploadTime;
	private String productPath;
	private String productDesc;
	private Integer productTypes;
	private Integer productOrder;
	private String workId;
	private String productUser;
	private String productRemark;
	private Integer productDel;

	// Constructors

	/** default constructor */
	public ProductInfo() {
	}

	/** full constructor */
	public ProductInfo(String productName, Date uploadTime, String productPath,
			Integer productTypes, Integer productOrder, Integer workId,
			String productUser, String productRemark, Integer productDel) {
		this.productName = productName;
		this.uploadTime = uploadTime;
		this.productPath = productPath;
		this.productDesc = productDesc;
		this.productTypes = productTypes;
		this.productOrder = productOrder;
		this.workId = workId;
		this.productUser = productUser;
		this.productRemark = productRemark;
		this.productDel = productDel;
	}

	// Property accessors

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getProductPath() {
		return this.productPath;
	}

	public void setProductPath(String productPath) {
		this.productPath = productPath;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public Integer getProductTypes() {
		return this.productTypes;
	}

	public void setProductTypes(Integer productTypes) {
		this.productTypes = productTypes;
	}

	public Integer getProductOrder() {
		return this.productOrder;
	}

	public void setProductOrder(Integer productOrder) {
		this.productOrder = productOrder;
	}

	public String getWorkId() {
		return this.workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getProductUser() {
		return this.productUser;
	}

	public void setProductUser(String productUser) {
		this.productUser = productUser;
	}

	public String getProductRemark() {
		return this.productRemark;
	}

	public void setProductRemark(String productRemark) {
		this.productRemark = productRemark;
	}

	public Integer getProductDel() {
		return this.productDel;
	}

	public void setProductDel(Integer productDel) {
		this.productDel = productDel;
	}

}