package com.actions;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.opensymphony.xwork2.ActionSupport;
import com.services.IWorkService;
import com.util.JsonDateValueProcessor;
import com.util.LoginUserUtil;
import com.vo.WorksInfos;

public class WorkAction extends ActionSupport{
	
	Logger logger = Logger.getLogger(this.getClass());
	
	private JSONObject result;
	private IWorkService workService;
	private String productGroupId;
	private String workName;
	private String workComment;
	private String workType;
	private int currentPage;
	private int recordSize;
	private String activityId;
	private String workId;
	private String contestId;
	private String userName;
	
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
			workService.doAddWork(userId, workName, workComment, productGroupId, activityId,contestId);
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
			Integer workTypeInteger = null ;
			if (workType != null && "".equals(workType)) {
				workTypeInteger = Integer.valueOf(workType);
			}
			result = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			result.put("works", JSONArray.fromObject(workService.queryProductByWorkType(workTypeInteger), jsonConfig));
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
			Integer workTypeInteger = null ;
			if (workType != null && !"".equals(workType)) {
				workTypeInteger = Integer.valueOf(workType);
			}
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			List<WorksInfos> queryWorks = workService.queryWorks(currentPage, recordSize,workName,userName,null,workTypeInteger);
			result.put("worksInfo", JSONArray.fromObject(queryWorks, jsonConfig));
			result.put("worksCount", workService.countWorks(workName,userName,null,workTypeInteger));
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
			result.put("worksCount", workService.countWorks(null,null,null,null));
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
	
	//根据大赛id，查询作品
	public String queryAllWorksByContestId(){
		logger.info("WorkAction.queryAllWorksByContestId start·····");
		result = new JSONObject();
		if(StringUtils.isBlank(contestId)){
			result.put("returnCode", "10");
			result.put("retunMsg", "参数错误");
			return SUCCESS;
		}
		try {
			result.put("contestsWorkInfo", workService.doQueryWorksInfoByContestId(contestId));
			result.put("returnCode", "00");
			result.put("retunMsg", "查询大赛作品成功");
		} catch (Exception e) {
			logger.error("查询大赛作品失败", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
		}
		return SUCCESS;
	}
	
	//根据活动id查询作品
	public String queryWorksByActivityId(){
		logger.info("WorkAction.queryWorksByActivityId start·····");
		result = new JSONObject();
		if(StringUtils.isBlank(activityId)){
			result.put("returnCode", "10");
			result.put("returnMsg", "查询活动作品信息异常");
		}
		try {
			result.put("worksInfos", workService.queryWorksByActivityId(activityId));
		} catch (Exception e) {
			logger.error("查询活动作品信息异常", e);
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

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
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

	public String getContestId() {
		return contestId;
	}

	public void setContestId(String contestId) {
		this.contestId = contestId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
