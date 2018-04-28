package com.actions;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.opensymphony.xwork2.ActionSupport;
import com.services.IWorkService;
import com.util.JsonDateValueProcessor;
import com.util.LoginUserUtil;

public class WorkAction extends ActionSupport{
	
	Logger logger = Logger.getLogger(this.getClass());
	
	private JSONObject result;
	private IWorkService workService;
	private String productGroupId;
	private String workName;
	private String workComment;
	private int workType;
	private int currentPage;
	private int recordSize;
	private String activityId;
	private String workId;
	
	public String addWork(){
		logger.info("WorkAction.addWork start·····");
		result = new JSONObject();
		String userId = LoginUserUtil.getUserInfo().getUserId();
		if(StringUtils.isBlank(userId)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			workService.doAddWork(userId, workName, workComment, productGroupId, activityId);
			result.put("returnCode", "00");
			result.put("returnMsg", "上传作品成功");
		} catch (Exception e) {
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
			logger.error("上传作品失败", e);
		}
		return SUCCESS;
	}
	
	/**
	 * @return
	 * 分类查询作品
	 * */
	public String queryWorksByType() {
		logger.info("ProductOperatorAction.queryProductByType start·····");
		try {
			result = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			result.put("works", JSONArray.fromObject(workService.queryProductByWorkType(workType), jsonConfig));
			result.put("returnCode", "00");
			result.put("returnMsg", "查询成功");
		} catch (Exception e) {
			logger.error("分类查询作品失败", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
		}
		return SUCCESS;
	}
	
	//查询所有作品（管理员侧）
	public String queryAllWorks(){
		logger.info("WorkAction.queryAllWorks start ······");
		result = new JSONObject();
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			result.put("worksInfo", JSONArray.fromObject(workService.queryWorks(currentPage, recordSize), jsonConfig));
			result.put("worksCount", workService.countWorks());
			result.put("returnCode", "00");
			result.put("returnMsg", "查询作品成功");
		} catch (Exception e) {
			logger.error("查询作品信息异常", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
		}
		return SUCCESS;
	}
	
	//查询作品（用户侧）
	public String queryWorks(){
		logger.info("WorkAction.queryWorks start·····");
		result = new JSONObject();
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			result.put("worksInfo", JSONArray.fromObject(workService.queryWorksInfo(currentPage, recordSize), jsonConfig));
			result.put("worksCount", workService.countWorks());
			result.put("returnCode", "00");
			result.put("returnMsg", "查询作品成功");
		} catch (Exception e) {
			logger.error("查询作品信息异常", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
		}
		return SUCCESS;
	}

	public String queryWorksInfosByWorkId(){
		logger.info("WorkAction.queryWorksInfosByWorkId start·····");
		result = new JSONObject();
		if(StringUtils.isBlank(workId)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			result.put("worksInfos", JSONArray.fromObject(workService.queryWorksInfoByWorkId(workId), jsonConfig));
			result.put("returnCode", "00");
			result.put("returnMsg", "查询作品成功");
		} catch (Exception e) {
			logger.error("查询作品信息异常", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
		}
		return SUCCESS;
	}
	
	
	public JSONObject getResult() {
		return result;
	}
	public void setResult(JSONObject result) {
		this.result = result;
	}
	public IWorkService getWorkService() {
		return workService;
	}
	public void setWorkService(IWorkService workService) {
		this.workService = workService;
	}

	public String getProductGroupId() {
		return productGroupId;
	}

	public void setProductGroupId(String productGroupId) {
		this.productGroupId = productGroupId;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getWorkComment() {
		return workComment;
	}

	public void setWorkComment(String workComment) {
		this.workComment = workComment;
	}

	public int getWorkType() {
		return workType;
	}

	public void setWorkType(int workType) {
		this.workType = workType;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRecordSize() {
		return recordSize;
	}

	public void setRecordSize(int recordSize) {
		this.recordSize = recordSize;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}
}
