package com.vo;

public class CollectsVo {
	private String collectId;
	private String productId;
	private String userId;
	private String createTime;
	
	
	public CollectsVo(String collectId, String productId, String userId,
			String createTime) {
		super();
		this.collectId = collectId;
		this.productId = productId;
		this.userId = userId;
		this.createTime = createTime;
	}
	
	
	public CollectsVo() {
		super();
	}

	public String getCollectId() {
		return collectId;
	}
	public void setCollectId(String collectId) {
		this.collectId = collectId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	

}
