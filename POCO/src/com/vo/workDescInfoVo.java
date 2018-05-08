package com.vo;

public class workDescInfoVo{
	
	private String productPath;
	private String workId;
	private String workName;
	private String uploadTime;
	
	public workDescInfoVo(String productPath, String workId, String workName,
			String uploadTime) {
		super();
		this.productPath = productPath;
		this.workId = workId;
		this.workName = workName;
		this.uploadTime = uploadTime;
	}

	public workDescInfoVo() {
		super();
	}

	public String getProductPath() {
		return productPath;
	}

	public void setProductPath(String productPath) {
		this.productPath = productPath;
	}

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

}
