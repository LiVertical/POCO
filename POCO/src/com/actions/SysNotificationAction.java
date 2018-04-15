package com.actions;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.entities.Notification;
import com.opensymphony.xwork2.ActionSupport;
import com.services.ISysNotificationService;
import com.services.IUserService;
import com.util.JsonDateValueProcessor;
import com.util.LoginUserUtil;

public class SysNotificationAction extends ActionSupport{
	
	Logger logger = Logger.getLogger(this.getClass());
	private static final long serialVersionUID = 1L;
	private ISysNotificationService sysNotificationService;
	private IUserService userService;
	
	private JSONObject result;
	
	private String notificationId;
	
	private String notificationIds;
	
	private String readType;
	
	private Notification notification;
	
	private List<Notification> notifications;
	
	private int currentPage;
	
	private int recordSize;
	
	private String notificationTitle;
	
	private String notificationInfo;
	
	/**
	 * 根据用户ID查询通知集合
	 * 根据时间倒叙展示
	 * @return
	 */
	public String notificationList(){
		logger.info("SysNotificationAction.notificationList start······");
		//创建返回JSON
		result = new JSONObject();
		//获取session
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession(); 
		//获取用户userId
		String userId = (String) session.getAttribute("userId");
		if(StringUtils.isBlank(userId)){
			result.put("returnCode", "10");
			result.put("renturnMsg", "参数错误");
			return SUCCESS;
		}
		//根据userId获取通知集合
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			result.put("notificationList", JSONArray.fromObject(sysNotificationService.notificationListByUserId(userId, currentPage, recordSize), jsonConfig));
			result.put("notificationTotalCount", sysNotificationService.notificationListByUserId(userId));
			result.put("returnCode", "00");
			result.put("renturnMsg", "查询成功");
		} catch (Exception e) {
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
			logger.error("查询异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 后台查询通知列表
	 * @return
	 */
	public String queryNotifications(){
		logger.info("SysNotificationAction.queryNotifiactions start·····");
		result = new JSONObject();
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			String userId = LoginUserUtil.getUserInfo().getUserId();
			int role = LoginUserUtil.getUserInfo().getRole();
			result.put("notifications", JSONArray.fromObject(sysNotificationService.queryNotifiactions(userId, role, currentPage,recordSize), jsonConfig));
			result.put("totalNotifiactionsCount", sysNotificationService.queryTotalNotification(userId, role));
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
	public String showDetial(){
		return "detail";
	}
	/**
	 * 查看一条通知
	 * @return
	 */
	public String showNotification(){
		logger.info("SysNotificationAction.showNotification start·····");
		result = new JSONObject();
		if(StringUtils.isBlank(notificationId)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			result.put("notificationInfos", sysNotificationService.showNotificationById(notificationId));
			result.put("returnCode", "00");
			result.put("returnMsg", "获取通知详情成功");
		} catch (Exception e) {
			logger.error("获取通知异常", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
		}
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
			result.put("msg", "已读全部消息");
			return SUCCESS;
		}else if(readType.equals("list") && notificationIds != null){
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
		if(StringUtils.isBlank(notificationTitle)||StringUtils.isBlank(notificationInfo)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		String userId = LoginUserUtil.getUserInfo().getUserId();
		if(userId == null || userId.equals("")){
			result.put("returnCode", "-1");
			result.put("returnMsg", "获取用户异常");
			return SUCCESS;
		}
		try {
			String userName = LoginUserUtil.getUserInfo().getUserName();
			result.put("count", sysNotificationService.adds(notificationTitle, notificationInfo, userId, userName));
			result.put("returnCode", "00");
			result.put("returnMsg", "发布成功");
		} catch (Exception e) {
			logger.error("发布通知失败", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
		}
		return SUCCESS;
	}
	
	//用户删除通知
	public String delNotification(){
		logger.info("SysNotificationAction.delNotifiaction start·····");
		result = new JSONObject();
		if(StringUtils.isBlank(notificationId)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数异常");
			return SUCCESS;
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String userId = session.getAttribute("userId").toString();
		if(userId == null || userId.equals("")){
			result.put("returnCode", "20");
			result.put("returnMsg", "获取当前用户异常");
			return SUCCESS;
		}
		try {
			sysNotificationService.deleteNotificationByNotificationId(notificationId);
			result.put("returnCode", "00");
			result.put("returnMsg", "删除通知成功");
		} catch (Exception e) {
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
			logger.error("删除通知异常", e);
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

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public String getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

	public String getNotificationIds() {
		return notificationIds;
	}

	public void setNotificationIds(String notificationIds) {
		this.notificationIds = notificationIds;
	}

	public String getReadType() {
		return readType;
	}

	public void setReadType(String readType) {
		this.readType = readType;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
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

	public String getNotificationTitle() {
		return notificationTitle;
	}

	public void setNotificationTitle(String notificationTitle) {
		this.notificationTitle = notificationTitle;
	}

	public String getNotificationInfo() {
		return notificationInfo;
	}

	public void setNotificationInfo(String notificationInfo) {
		this.notificationInfo = notificationInfo;
	}

}
