package com.actions;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.services.IActivityService;

public class ActivityAction extends ActionSupport{
	
	Logger logger = Logger.getLogger(this.getClass());
	private String activityId;
	private String activityName;
	private JSONObject result; 
	private IActivityService activityService;
	
	//用户申请活动
	public String applyActivity(){
		logger.info("ActivityAction.applyActivity start·····");
		
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
}
