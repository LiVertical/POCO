package com.actions;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.opensymphony.xwork2.ActionSupport;
import com.services.IActivityService;
import com.util.JsonDateValueProcessor;

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
	private int currentPage;
	private int pageSize;
	private int curStatus;
	
	//用户申请活动
	public String applyActivity(){
		logger.info("ActivityAction.applyActivity start·····");
		result = new JSONObject();
		try{
			if(StringUtils.isBlank(activityName)||StringUtils.isBlank(userId)||StringUtils.isBlank(activityDesc)){
				result.put("returnCode", "10");
				result.put("returnMsg", "参数错误");
				return SUCCESS;
			}
			activityService.doApplyActivity(userId, activityName,  activityDesc, createTime, endTime);
			result.put("returnCode", "00");
			result.put("returnMsg", "申请活动成功");
	} catch (Exception e) {
		logger.error("保存活动异常", e);
		result.put("returnCode", "-1");
		result.put("returnMsg", "内部服务器异常");
	}
		return SUCCESS;
	}
	
	//用户参加活动
	public String joinActivity(){
		logger.info("ActivityAction.joinActivity start·····");
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
	public String auditActivity(){
		logger.info("ActivityAction.auditActivity start·····");
		result = new JSONObject();
		if(StringUtils.isBlank(activityId) || StringUtils.isBlank(String.valueOf(curStatus))){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			 activityService.doAuditActivity(activityId, curStatus);
			result.put("returnCode", "00");
			result.put("returnMsg", "审批活动成功");
		} catch (Exception e) {
			logger.error("审批活动异常", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
		}
		return SUCCESS;
	} 
	
	
	//查询所有活动（管理员）
	public String queryAllActivities(){
		logger.info("ActivityAction.queryAllActivities start·····");
		result = new JSONObject();
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			result.put("activitiesInfos", JSONArray.fromObject(activityService.queryAllActivitiesByCondition(), jsonConfig));
			result.put("totalActivitiesCount", activityService.queryAllActivitiesCount());
			result.put("returnCode", "00");
			result.put("returnMsg", "查询成功");
		} catch (Exception e) {
			logger.error("查询活动详情异常", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
		}
		return SUCCESS;
	}
	
	//查询所有活动（普通用户）
		public String getAllActivities(){
			logger.info("ActivityAction.getAllActivities start·····");
			result = new JSONObject();
			try {
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
				result.put("activitiesInfos", JSONArray.fromObject(activityService.queryAllActivities(), jsonConfig));
				result.put("totalActivitiesCount", activityService.queryAllActivitiesCount());
				result.put("returnCode", "00");
				result.put("returnMsg", "查询成功");
			} catch (Exception e) {
				logger.error("查询活动详情异常", e);
				result.put("returnCode", "-1");
				result.put("returnMsg", "内部服务器异常");
			}
			return SUCCESS;
		}
	
	//展示活动详情
		public String queryActivityInfo(){
			logger.info("ActivityAction.queryActivityInfo start·····");
			result = new JSONObject();
			if(StringUtils.isBlank(activityId)){
				result.put("returnCode", "10");
				result.put("returnMsg", "参数错误");
				return SUCCESS;
			}
			try {
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
				result.put("activityInfos", JSONArray.fromObject(activityService.doQueryActivityInfo(activityId), jsonConfig));
				result.put("returnCode", "00");
				result.put("returnMsg", "查询活动详情成功");
			} catch (Exception e) {
				logger.error("根据活动id查询活动详情异常", e);
				result.put("returnCode", "-1");
				result.put("returnMsg", "内部服务器异常");
			}
			return SUCCESS;
		}


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

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurStatus() {
		return curStatus;
	}

	public void setCurStatus(int curStatus) {
		this.curStatus = curStatus;
	}
	
}
