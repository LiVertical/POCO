package com.vo;

import java.util.Comparator;
import java.util.List;

import com.entities.ProductInfo;

public class WorksInfos implements Comparable<WorksInfos>{
	
	private String workId;
	private String workName;
	private String workUploadTime;
	private String workComment;
	private String userId;
	private List<ProductInfo> productInfos;
	private String userName;
	private int voteNum;
	
	public WorksInfos() {
		super();
	}
	
	public WorksInfos(String workId, String workName, String workUploadTime,
			String workComment, String userId, List<ProductInfo> productInfos,String userName, int voteNum) {
		super();
		this.workId = workId;
		this.workName = workName;
		this.workUploadTime = workUploadTime;
		this.workComment = workComment;
		this.userId = userId;
		this.productInfos = productInfos;
		this.userName = userName;
		this.voteNum = voteNum;
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
	public String getWorkUploadTime() {
		return workUploadTime;
	}
	public void setWorkUploadTime(String workUploadTime) {
		this.workUploadTime = workUploadTime;
	}
	public String getWorkComment() {
		return workComment;
	}
	public void setWorkComment(String workComment) {
		this.workComment = workComment;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<ProductInfo> getProductInfos() {
		return productInfos;
	}
	public void setProductInfos(List<ProductInfo> productInfos) {
		this.productInfos = productInfos;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getVoteNum() {
		return voteNum;
	}

	public void setVoteNum(int voteNum) {
		this.voteNum = voteNum;
	}

	@Override
	public int compareTo(WorksInfos o) {
		if (this.voteNum > o.getVoteNum()) {
            return -1;
        } else {
            return 1;
        }
	}


	
}
