package com.entities;

import java.util.Date;

/**
 * Notifiaction entity. @author MyEclipse Persistence Tools
 */

public class Notifiaction implements java.io.Serializable {

	// Fields

	private String notifiactionId;
	private String notifiactionInfo;
	private String userId;
	private Date createTime;
	private Integer usefulLife;
	private String curStatus;

	// Constructors

	/** default constructor */
	public Notifiaction() {
	}

	/** minimal constructor */
	public Notifiaction(String notifiactionInfo) {
		this.notifiactionInfo = notifiactionInfo;
	}

	/** full constructor */
	public Notifiaction(String notifiactionInfo, String userId,
			Date createTime, Integer usefulLife, String curStatus) {
		this.notifiactionInfo = notifiactionInfo;
		this.userId = userId;
		this.createTime = createTime;
		this.usefulLife = usefulLife;
		this.curStatus = curStatus;
	}

	// Property accessors

	public String getNotifiactionId() {
		return this.notifiactionId;
	}

	public void setNotifiactionId(String notifiactionId) {
		this.notifiactionId = notifiactionId;
	}

	public String getNotifiactionInfo() {
		return this.notifiactionInfo;
	}

	public void setNotifiactionInfo(String notifiactionInfo) {
		this.notifiactionInfo = notifiactionInfo;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUsefulLife() {
		return this.usefulLife;
	}

	public void setUsefulLife(Integer usefulLife) {
		this.usefulLife = usefulLife;
	}

	public String getCurStatus() {
		return this.curStatus;
	}

	public void setCurStatus(String curStatus) {
		this.curStatus = curStatus;
	}

}