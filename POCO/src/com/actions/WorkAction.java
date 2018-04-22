package com.actions;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.services.IWorkService;
import com.util.LoginUserUtil;

public class WorkAction extends ActionSupport{
	
	Logger logger = Logger.getLogger(this.getClass());
	
	private JSONObject result;
	private IWorkService workService;
	private String productGroupId;
	private String workName;
	private String workComment;
	
	public String addWork(){
		logger.info("WorkActio.addWork start·····");
		result = new JSONObject();
		String userId = LoginUserUtil.getUserInfo().getUserId();
		if(StringUtils.isBlank(userId)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			workService.doAddWork(userId, workName, workComment, productGroupId);
			result.put("returnCode", "00");
			result.put("returnMsg", "上传作品成功");
		} catch (Exception e) {
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
			logger.error("上传作品失败", e);
		}
		return SUCCESS;
	}
	
	public String queryAllWorks(){
		
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
	
}
