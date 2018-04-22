package com.actions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.services.ICommentsService;
import com.util.JsonDateValueProcessor;
import com.util.LoginUserUtil;

public class CommentsAction extends ActionSupport  {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	private String commentUser;
	private String commentId;
	private String productId;
	private ICommentsService commentsService;
	private String commentDesc;
	private JSONObject result;
	private int currentPage;
	private int recordSize;
	
	//添加评论
	public String addComments(){
		logger.info("CommentsAction.addComments start······");
		result = new JSONObject();
		commentUser = LoginUserUtil.getUserInfo().getUserId();
		if(StringUtils.isBlank(commentUser)||StringUtils.isBlank(productId)||StringUtils.isBlank(commentDesc)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			commentDesc = new String(commentDesc.getBytes("ISO-8859-1"),"UTF-8");
			commentsService.doAddComments(commentUser, productId, commentDesc);
			result.put("returnCode", "00");
			result.put("returnMsg", "评论成功");
		} catch (Exception e) {
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
			logger.error("添加评论失败", e);
		}
		return SUCCESS;
	}
	
	//删除评论
	public String deleteCommentByCommentId(){
		logger.info("CommentsAction.deleteCommentsByProductId start·····");
		result = new JSONObject();
		if(StringUtils.isBlank(commentId)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			commentsService.deleteCommentByCommentId(commentId);
			result.put("returnCode", "00");
			result.put("returnMsg", "删除用户评论成功");
		} catch (Exception e) {
			logger.error("删除评论异常", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
		}
		
		return SUCCESS;
	}
	
	//查看用户的所有评论
	public String queryAllCommentsByUser(){
		logger.info("CommentsAction.queryAllCommentsByUser start·····");
		result = new JSONObject();
		try {
			String commentUser = LoginUserUtil.getUserInfo().getUserId();
			if(StringUtils.isBlank(commentUser)){
				result.put("returnCode", "10");
				result.put("returnMsg", "参数错误");
				return SUCCESS;
			}
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			result.put("commentsInfosOfUser", JSONArray.fromObject(commentsService.queryAllCommentInfosbyUser(commentUser, currentPage, recordSize), jsonConfig));
			result.put("commentsTotalsCountOfUser", commentsService.queryCommentsCountOfUser(commentUser));
			result.put("returnCode", "00");
			result.put("returnMsg", "查询用户评论成功");
		} catch (Exception e) {
			logger.error("查询评论异常", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
		}
		return SUCCESS;
	}
	
	//查看作品的所有评论
	public String queryAllComments(){
		logger.info("CommentsAction.queryAllComments start·····");
		result = new JSONObject();
		if(StringUtils.isBlank(productId)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			result.put("commentsInfos", JSONArray.fromObject(commentsService.queryAllCommentInfos(productId, currentPage, recordSize), jsonConfig));
			result.put("commentsTotalsCount", commentsService.queryCommentsCounts(productId));
			result.put("returnCode", "00");
			result.put("returnMsg", "查询评论成功");
		} catch (Exception e) {
			logger.error("查询评论异常", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
		}
		return SUCCESS;
	}
	
	
	public String getCommentUser() {
		return commentUser;
	}
	public void setCommentUser(String commentUser) {
		this.commentUser = commentUser;
	}
	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}

	public ICommentsService getCommentsService() {
		return commentsService;
	}

	public void setCommentsService(ICommentsService commentsService) {
		this.commentsService = commentsService;
	}

	public String getCommentDesc() {
		return commentDesc;
	}
	public void setCommentDesc(String commentDesc) {
		this.commentDesc = commentDesc;
	}
	public JSONObject getResult() {
		return result;
	}
	public void setResult(JSONObject result) {
		this.result = result;
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
}