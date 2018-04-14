package com.actions;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.entities.Notifiaction;
import com.opensymphony.xwork2.ActionSupport;
import com.services.ISysNotificationService;
import com.services.IUserService;
import com.util.JsonDateValueProcessor;

public class SysNotificationAction extends ActionSupport{
	
	Logger logger = Logger.getLogger(this.getClass());
	private static final long serialVersionUID = 1L;
	private ISysNotificationService sysNotificationService;
	private IUserService userService;
	
	private JSONObject result;
	
	private String notifiactionId;
	
	private String notifiactionIds;
	
	private String readType;
	
	private Notifiaction notifiaction;
	
	private List<Notifiaction> notifiactions;
	
	private int currentPage;
	
	private int recordSize;
	
	/**
	 * 根据用户ID查询通知集合
	 * 根据时间倒叙展示
	 * @return
	 */
	public String notificationList(){
		//创建返回JSON
		result = new JSONObject();
		//获取session
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession(); 
		//获取用户userId
		String userId = (String) session.getAttribute("userId");
		//根据userId获取通知集合
		List<Notifiaction> notifications = sysNotificationService.notificationListByUserId(userId);
		result.put("dataList", notifications);
		return SUCCESS;
	}
	
	/**
	 * 后台查询通知列表
	 * @return
	 */
	public String queryNotifiactions(){
		logger.info("SysNotificationAction.queryNotifiactions start·····");
		result = new JSONObject();
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			result.put("notifiactions", JSONArray.fromObject(sysNotificationService.queryNotifiactions(currentPage,recordSize), jsonConfig));
			result.put("totalNotifiactionsCount", sysNotificationService.queryTotalNotification());
			result.put("receiverCount", sysNotificationService.countReceiver());
			result.put("returnCode", "00");
			result.put("returnMsg", "查询通知成功");
		} catch (Exception e) {
			logger.error("查询系统通知异常！", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
		}
		return SUCCESS;
	}
	/**
	 * 查看一条通知
	 * @return
	 */
	public String showNotification(){
		result = new JSONObject();
		
		Notifiaction notifiaction = sysNotificationService.showNotificationById(notifiactionId);
		result.put("data", notifiaction);
		return SUCCESS;
	}
	
	/**
	 * 已读消息
	 * @return
	 */
	public String readMessage(){
		result = new JSONObject();
		if(readType == null){
			result.put("msg", "系统异常");
			return ERROR;
		}
		
		if( readType.equals("all")){
//			sysNotificationService.readMessageAll();
			result.put("msg", "已读全部消息");
			return SUCCESS;
		}else if(readType.equals("list") && notifiactionIds != null){
//			JSONObject
//			sysNotificationService.readMessage();
			return SUCCESS;
		}else{
			result.put("msg", "系统异常");
			return ERROR;
		}
	}
	
	/**
	 * 发布通知
	 * @return
	 */
	public String addNotification(){
		logger.info("SysNotificationAction.addNotification start·····");
		result = new JSONObject();
		if(notifiaction.getNotifiactionTitle() == null || notifiaction.getNotifiactionInfo() == null){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String userId = session.getAttribute("userId").toString();
		if(userId == null || userId.equals("")){
			result.put("returnCode", "-1");
			result.put("returnMsg", "获取用户异常");
			return SUCCESS;
		}
		notifiaction.setCreateUser(userId);
		try {
			int count = sysNotificationService.adds(notifiaction);
			result.put("count", count);
			result.put("returnCode", "00");
			result.put("returnMsg", "发布成功");
		} catch (Exception e) {
			logger.error("发布通知失败", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
		}
		return SUCCESS;
	}
	
	public String delNotifiaction(){
		result = new JSONObject();
		if(notifiactions.size() < 1){
			result.put("msg", "删除通知少于1条");
			return ERROR;
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String userId = session.getAttribute("userId").toString();
		if(userId == null || userId.equals("")){
			result.put("msg", "获取用户异常");
			return ERROR;
		}
		for (Notifiaction notifiaction2 : notifiactions) {
			if(notifiaction2 == null && notifiaction2.getNotifiactionGroupId() == null && notifiaction2.getNotifiactionId() == null){
				result.put("msg", "无效的通知");
				return ERROR;
			}
			if(notifiaction2.getNotifiactionGroupId() != null && !notifiaction2.getNotifiactionGroupId().equals("")
					&& notifiaction2.getNotifiactionId() != null && !notifiaction2.getNotifiactionId().equals("")){
				result.put("msg", "异常的通知");
				return ERROR;
			}
			notifiaction2.setUserId(userId);
			sysNotificationService.delete(notifiaction2);
		}
		return SUCCESS;
	}
	
	
	public ISysNotificationService getSysNotificationService() {
		return sysNotificationService;
	}

	public void setSysNotificationService(
			ISysNotificationService sysNotificationService) {
		this.sysNotificationService = sysNotificationService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public String getNotifiactionId() {
		return notifiactionId;
	}

	public void setNotifiactionId(String notifiactionId) {
		this.notifiactionId = notifiactionId;
	}

	public String getNotifiactionIds() {
		return notifiactionIds;
	}

	public void setNotifiactionIds(String notifiactionIds) {
		this.notifiactionIds = notifiactionIds;
	}

	public String getReadType() {
		return readType;
	}

	public void setReadType(String readType) {
		this.readType = readType;
	}

	public List<Notifiaction> getNotifiactions() {
		return notifiactions;
	}

	public void setNotifiactions(List<Notifiaction> notifiactions) {
		this.notifiactions = notifiactions;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
	public Notifiaction getNotifiaction() {
		return notifiaction;
	}
	public void setNotifiaction(Notifiaction notifiaction) {
		this.notifiaction = notifiaction;
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
