package com.actions;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.services.IVoteService;
import com.util.LoginUserUtil;

public class VoteAction extends ActionSupport{
	
	Logger logger = Logger.getLogger(this.getClass());
	
	private IVoteService voteService;
	private String workId;
	private String userId;
	private JSONObject result;
	
	public String vote(){
		logger.info("VoteAction.vote start·····");
		result = new JSONObject();
		String userId = LoginUserUtil.getUserInfo().getUserId();
		if(StringUtils.isBlank(workId)||StringUtils.isBlank(userId)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			boolean voted = voteService.doVote(userId,workId);
			if(!voted){
				result.put("returnCode", "01");
				result.put("returnMsg", "您已经为该作品投票了");
			}else{
				result.put("returnCode", "00");
				result.put("returnMsg", "投票成功");
			}
		} catch (Exception e) {
			logger.error("投票失败", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 查询作品获赞数目*/
	public String getVoteNum(){
		logger.info("VoteAction.getVoteNum start······");
		result = new JSONObject();
		if(StringUtils.isBlank(workId)||StringUtils.isBlank(userId)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
		}
		try {
			result.put("voteNum", voteService.getVoteNum(workId));
			result.put("returnMsg", "获取投票数目成功");
		} catch (Exception e) {
			logger.error("获取投票数目失败", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
		}
		return SUCCESS;
	}
	
	public IVoteService getVoteService() {
		return voteService;
	}
	public void setVoteService(IVoteService voteService) {
		this.voteService = voteService;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public JSONObject getResult() {
		return result;
	}
	public void setResult(JSONObject result) {
		this.result = result;
	}
}
