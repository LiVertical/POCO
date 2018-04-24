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
	
}
