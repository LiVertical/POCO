package com.actions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.services.ILikeService;
import com.util.JsonDateValueProcessor;
import com.util.LoginUserUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class LikesAction extends ActionSupport{
	
	Logger logger = Logger.getLogger(this.getClass());
	
	private String userId;
	private String id;
	private String productId;
	private String productName;
	private JSONObject result;
	private ILikeService likeService;
	
	//用户点赞
	public String getTags(){
		logger.info("LikesAction.getTags start ·····");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		userId = session.getAttribute("userId").toString();
		result = new JSONObject();
		if(StringUtils.isBlank(userId)||StringUtils.isBlank(productId)||StringUtils.isBlank(productName)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			likeService.setTags(userId, productId, productName);
			result.put("returnCode", "00");
			result.put("returnMsg", "点赞成功!");
		} catch (Exception e) {
			logger.error("内部服务器异常", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "点赞失败,内部服务器异常!");
		}
		return SUCCESS;
	}
	
	//取消点赞
	public String cancleLike(){
		result = new JSONObject();
		logger.info("LikesAction.cancleLike start ·····");
		if(StringUtils.isBlank(id)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			likeService.doCancleLike(id);
			result.put("returnCode", "00");
			result.put("returnMsg", "取消点赞成功");
		} catch (Exception e) {
			logger.error("内部服务器异常", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "取消点赞失败");
		}
		return SUCCESS;
	}

	//获取作品点赞的数目
	public String getLikesNum(){
		logger.info("LikesAction.getLikesNum start ·····");
		result = new JSONObject();
		if(StringUtils.isBlank(productId)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			result.put("likesNum", likeService.doGetLikesNum(productId));
			result.put("returnCode", "00");
			result.put("returnMsg", "获取点赞数目成功");
		} catch (Exception e) {
			logger.error("获取点赞数目失败", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
		}
		return SUCCESS;
	}
	//查询每个用户点赞的作品
	public String getProductByUserId(){
		logger.info("LikesAction.getProductByUserId start ·····");
		result = new JSONObject();
		userId = LoginUserUtil.getUserInfo().getUserId();
		if(StringUtils.isBlank(userId)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			result.put("products", JSONArray.fromObject(likeService.queryProductByUserId(userId), jsonConfig));
			result.put("returnCode", "00");
			result.put("returnMsg", "查询成功");
		} catch (Exception e) {
			logger.error("根据userId查询点赞作品失败", e);
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public ILikeService getLikeService() {
		return likeService;
	}

	public void setLikeService(ILikeService likeService) {
		this.likeService = likeService;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}
