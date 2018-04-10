package com.actions;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.services.IActivityService;

public class ActivityAction extends ActionSupport{
	
	Logger logger = Logger.getLogger(this.getClass());
	private String activityId;
	private String activityName;
	private String activityDesc;
	private String activityInfo;
	private String createTime;
	private String endTime;
	private String userId;
	private List<File> products;
	private List<String> productIds;
	private List<String> productNames;
	private JSONObject result; 
	private IActivityService activityService;
	
	//用户申请活动
	public String applyActivity(){
		logger.info("ActivityAction.applyActivity start·····");
		if(StringUtils.isBlank(activityName)||StringUtils.isBlank(userId)||
				StringUtils.isBlank(activityDesc)||StringUtils.isBlank(activityInfo)||products == null){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			activityService.doSaveActivity(activityName, products, productIds, productNames, userId, activityDesc, activityInfo, createTime, endTime);
		} catch (Exception e) {
			logger.error("保存活动异常", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
		}
		result.put("returnCode", "00");
		result.put("returnMsg", "申请成功");
		return SUCCESS;
	}
	
	//管理员审批活动
	
	//查询所有活动
	
	//参加活动
	
	//展示活动详情
	
	//


	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityDesc() {
		return activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}

	public String getActivityInfo() {
		return activityInfo;
	}

	public void setActivityInfo(String activityInfo) {
		this.activityInfo = activityInfo;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<File> getProducts() {
		return products;
	}

	public void setProducts(List<File> products) {
		this.products = products;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public IActivityService getActivityService() {
		return activityService;
	}

	public void setActivityService(IActivityService activityService) {
		this.activityService = activityService;
	}

	public List<String> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<String> productIds) {
		this.productIds = productIds;
	}

	public List<String> getProductNames() {
		return productNames;
	}

	public void setProductNames(List<String> productNames) {
		this.productNames = productNames;
	}
	
}
