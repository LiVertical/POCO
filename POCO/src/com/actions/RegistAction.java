package com.actions;
import java.io.InputStream;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.entities.Users;
import com.opensymphony.xwork2.ActionSupport;
import com.services.IRegistService;

public class RegistAction extends ActionSupport{
	
	Logger logger = Logger.getLogger(this.getClass());
	private static final long serialVersionUID = -5569698099319677051L;
	private IRegistService registService;	
	private Users user;	
	private InputStream inputStream;
	private String loginName;
	private String loginPass;
	private JSONObject result;
	private String userName;
	private int role;
		
	/***
	 * @return
	 * 用户注册
	 * */
	public String userInfoSave(){
		logger.info("RegistAction.userInfoSave start·····");
		result = new JSONObject();
		role = 0;
		if(StringUtils.isBlank(loginName)||StringUtils.isBlank(loginPass)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数异常");
			return SUCCESS;
		}
		try {
			boolean isExist = false;
			isExist = registService.findUserByLoginName(loginName);
			if(!isExist){
				registService.saveUserInfo(loginName, loginPass, role);
				result.put("returnCode", "00");
				result.put("returnMsg", "用户注册成功");
			}
			else{
				result.put("returnCode", "20");
				result.put("returnMsg", "该用户名已存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("returnCode", "10");
			result.put("returnMsg", "用户信息注册异常");
		}
		return SUCCESS;		
	}
	
	/***
	 * @return
	 * 添加系统管理员
	 * */
	public String adminUserRegist(){
		logger.info("adminUserRegist start·····");
		result = new JSONObject();
		if(StringUtils.isBlank(loginName) || StringUtils.isBlank(userName)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			boolean isExist = false;
			isExist = registService.findUserByLoginName(loginName);
			if(!isExist){
				registService.doAdminUserRegist(loginName, userName);
				result.put("returnCode", "00");
				result.put("returnMsg", "用户注册成功");
			}
			else{
				result.put("returnCode", "20");
				result.put("returnMsg", "该用户名已存在");
			}
		} catch (Exception e) {
			result.put("returnCode", "-1");
			result.put("returnMsg", "用户注册失败，服务器异常");
			logger.error("用户注册失败，内部服务器异常！", e);
		}
		return SUCCESS;
	}

	public IRegistService getRegistService() {
		return registService;
	}

	public void setRegistService(IRegistService registService) {
		this.registService = registService;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPass() {
		return loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
}
