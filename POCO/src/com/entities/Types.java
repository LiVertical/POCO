package com.entities;

import java.util.Date;

/**
 * Work entity. @author MyEclipse Persistence Tools
 */

public class Types implements java.io.Serializable {
	
	private String typeId;
	private String typeName;
	private String typeValue;
	private int typeOrder;
	private String fatherType;
	private Date createTime;
	private Date updateTime;
	
	public Types() {
		super();
	}
	
	public Types(String typeId, String typeName, String typeValue,
			int typeOrder, String fatherType, Date createTime, Date updateTime) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
		this.typeValue = typeValue;
		this.typeOrder = typeOrder;
		this.fatherType = fatherType;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeValue() {
		return typeValue;
	}
	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}
	public int getTypeOrder() {
		return typeOrder;
	}
	public void setTypeOrder(int typeOrder) {
		this.typeOrder = typeOrder;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getFatherType() {
		return fatherType;
	}
	public void setFatherType(String fatherType) {
		this.fatherType = fatherType;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	

}