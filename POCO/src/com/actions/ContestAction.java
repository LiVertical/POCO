package com.actions;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.opensymphony.xwork2.ActionSupport;
import com.services.IContestService;
import com.util.JsonDateValueProcessor;

public class ContestAction extends ActionSupport{
	
	Logger logger = Logger.getLogger(this.getClass());
	
	private IContestService contestService;
	private String contestId;
	private String contestInfo;
	private String contestName;
	private String contestDesc;
	private Date startTime;
	private Date endTime;
	private JSONObject result;
	
	public String postContest(){
		logger.info("ContestAction.postContest start····");
		result = new JSONObject();
		if(StringUtils.isBlank(contestName)||StringUtils.isBlank(contestDesc)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			contestService.doPostContest(contestName, contestDesc, startTime, endTime);
			result.put("returnCode", "00");
			result.put("returnMsg", "发布成功！");
		} catch (Exception e) {
			logger.error("创建大赛失败", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
		}
		return SUCCESS;
	}
	
	public String getAllContests(){
		logger.info("ContestAction.getAllContests start········");
		result = new JSONObject();
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			result.put("contestInfo", JSONArray.fromObject(contestService.queryAllContest(), jsonConfig));
			result.put("returnCode", "00");
			result.put("returnMsg", "查询成功");
		} catch (Exception e) {
			logger.error("内部服务器异常", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "查询大赛失败");
		}
		return SUCCESS;
	}
	
	public String queryContestInfoByContestId(){
		logger.info("ContestAction.queryContestInfoByContestId start········");
		result = new JSONObject();
		if(StringUtils.isBlank(contestId)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			result.put("contestInfo", JSONArray.fromObject(contestService.queryContestInfoByContestId(contestId), jsonConfig));
			result.put("returnCode", "00");
			result.put("returnMsg", "查询活动内容成功");
		} catch (Exception e) {
			logger.info("查询活动异常", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "查询活动异常");
		}
		
		return SUCCESS;
	}
	
	public IContestService getContestService() {
		return contestService;
	}

	public void setContestService(IContestService contestService) {
		this.contestService = contestService;
	}

	public String getContestId() {
		return contestId;
	}

	public void setContestId(String contestId) {
		this.contestId = contestId;
	}

	public String getContestInfo() {
		return contestInfo;
	}

	public void setContestInfo(String contestInfo) {
		this.contestInfo = contestInfo;
	}

	public String getContestName() {
		return contestName;
	}

	public void setContestName(String contestName) {
		this.contestName = contestName;
	}

	public String getContestDesc() {
		return contestDesc;
	}

	public void setContestDesc(String contestDesc) {
		this.contestDesc = contestDesc;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
	
}
