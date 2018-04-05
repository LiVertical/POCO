package com.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.entities.Notifiaction;
import com.entities.Users;
import com.opensymphony.xwork2.ActionSupport;
import com.services.ISysNotificationService;
import com.services.IUserService;

public class SysNotificationAction extends ActionSupport{
	
	private ISysNotificationService sysNotificationService;
	private IUserService userService;
	
	private JSONObject result;
	
	private String notifiactionId;
	
	private String notifiactionIds;
	
	private String readType;
	
	private Notifiaction notifiaction;
	
	/**
	 * 根据用户ID查询通知集合
	 * 根据时间倒叙展示
	 * @return
	 */
	public String NotificationList(){
		//创建返回JSON
		result = new JSONObject();
		//获取session
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession(); 
		//获取用户userId
		int userId =  (Integer) session.getAttribute("userId");
		//根据userId获取通知集合
		List<Notifiaction> notifications = sysNotificationService.notificationListByUserId(userId);
		result.put("dataList", notifications);
		return SUCCESS;
	}
	
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
		List<Users> userList;
		try {
			userList = userService.queryUserByCondition(Integer.MAX_VALUE, 1);
		} catch (Exception e1) {
			e1.printStackTrace();
			return ERROR;
		}
		for (Users users : userList) {
//			notifiaction.setUserId(users.getUserId());
			try {
				sysNotificationService.add(notifiaction);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	public String delNotifiaction(){
		
		
		return null;
	}
	
	
	
	public ISysNotificationService getSysNotificationService() {
		return sysNotificationService;
	}

	public void setSysNotificationService(
			ISysNotificationService sysNotificationService) {
		this.sysNotificationService = sysNotificationService;
	}







	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

}
