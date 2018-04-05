package com.actions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.services.ICollectService;
import com.util.JsonDateValueProcessor;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class CollectAction extends ActionSupport{
	
	Logger logger = Logger.getLogger(this.getClass());

	private String id;
	private String userId;
	private String productId;
	private JSONObject result;
	private ICollectService collectService;
	
	//添加收藏
	public String addToCollect(){
		logger.info("CollectAction.addToCollect start·····");
		result = new JSONObject();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		userId = session.getAttribute("userId").toString();
		if(StringUtils.isBlank(productId)||StringUtils.isBlank(userId)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			collectService.doAddCollect(userId, productId);
			result.put("returnCode", "00");
			result.put("returnMsg", "收藏成功!");
		} catch (Exception e) {
			logger.error("收藏失败，内部服务器异常", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "收藏失败,内部服务器异常");
		}
		return SUCCESS;
	}
	
	//删除收藏
	public String deleteCollect(){
		logger.info("CollectAction.deleteCollect start·····");
		result = new JSONObject();
		if(StringUtils.isBlank(productId)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			collectService.doDelelteCollect(productId);
			result.put("returnCode", "00");
			result.put("returnMsg", "删除收藏成功!");
		} catch (Exception e) {
			logger.error("删除收藏失败，内部服务器异常", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "删除收藏失败,内部服务器异常");
		}
		return SUCCESS;
	}
	
	//根据用户id查看收藏
	public String queryCollectByUserId(){
		logger.info("CollectAction.deleteCollect start·····");
		result = new JSONObject();
		if(StringUtils.isBlank(userId)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			result.put("collectInfos", JSONArray.fromObject(collectService.doQueryCollectByUserId(userId), jsonConfig));
			result.put("returnCode", "00");
			result.put("returnMsg", "查询收藏内容成功!");
		} catch (Exception e) {
			logger.error("查看收藏失败，内部服务器异常",  e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "查看收藏失败,内部服务器异常");
		}
		return SUCCESS;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public JSONObject getResult() {
		return result;
	}
	public void setResult(JSONObject result) {
		this.result = result;
	}

	public ICollectService getCollectService() {
		return collectService;
	}

	public void setCollectService(ICollectService collectService) {
		this.collectService = collectService;
	}
	
}
