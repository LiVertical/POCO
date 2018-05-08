package com.vo;

import java.util.Date;

public class ProductInfosVo {
	
	private String productId;
	private String productName;
	private Date uploadTime;
	private String productPath;
	private String productDesc;
	private Integer productTypes;
	private Integer productOrder;
	private String workId;
	private String productUserName;
	private String productGroupId;
	
	public ProductInfosVo() {
		super();
	}

	public ProductInfosVo(String productId, String productName,
			Date uploadTime, String productPath, String productDesc,
			Integer productTypes, Integer productOrder, String workId,
			String productUserName, String productGroupId) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.uploadTime = uploadTime;
		this.productPath = productPath;
		this.productDesc = productDesc;
		this.productTypes = productTypes;
		this.productOrder = productOrder;
		this.workId = workId;
		this.productUserName = productUserName;
		this.productGroupId = productGroupId;
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
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getProductPath() {
		return productPath;
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
		return productTypes;
	}
	public void setProductTypes(Integer productTypes) {
		this.productTypes = productTypes;
	}
	public Integer getProductOrder() {
		return productOrder;
	}
	public void setProductOrder(Integer productOrder) {
		this.productOrder = productOrder;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getProductUserName() {
		return productUserName;
	}
	public void setProductUserName(String productUserName) {
		this.productUserName = productUserName;
	}
	public String getProductGroupId() {
		return productGroupId;
	}
	public void setProductGroupId(String productGroupId) {
		this.productGroupId = productGroupId;
	}

}
