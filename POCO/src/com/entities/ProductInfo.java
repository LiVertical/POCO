package com.entities;

import java.util.Date;

/**
 * Productinfo entity. @author MyEclipse Persistence Tools
 */

public class ProductInfo implements java.io.Serializable {

	// Fields

	private String productId;
	private String productName;
	private Date uploadTime;
	private String productPath;
	private String productDesc;
	private Integer productTypes;
	private Integer productOrder;
	private String workId;
	private String productUser;
	private String productGroupId;
	private Integer productDel;

	// Constructors

	/** default constructor */
	public ProductInfo() {
	}

	/** full constructor */
	public ProductInfo(String productName, Date uploadTime, String productPath,
			String productDesc, Integer productTypes, Integer productOrder,
			String workId, String productUser, String productRemark,
			Integer productDel) {
		this.productName = productName;
		this.uploadTime = uploadTime;
		this.productPath = productPath;
		this.productDesc = productDesc;
		this.productTypes = productTypes;
		this.productOrder = productOrder;
		this.workId = workId;
		this.productUser = productUser;
		this.productGroupId = productGroupId;
		this.productDel = productDel;
	}

	// Property accessors

	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
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
		return this.productDesc;
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

	public String getProductGroupId() {
		return productGroupId;
	}

	public void setProductGroupId(String productGroupId) {
		this.productGroupId = productGroupId;
	}

	public Integer getProductDel() {
		return this.productDel;
	}

	public void setProductDel(Integer productDel) {
		this.productDel = productDel;
	}

}