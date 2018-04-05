package com.actions;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.entities.Users;
import com.opensymphony.xwork2.ActionSupport;
import com.services.ILoginService;

/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class LoginAction extends ActionSupport{
	
	private ILoginService loginService;
	private InputStream inputStream;	
	private String loginName;
	private String passWord;
	private String loginPass;
	private int role;  //0:普通用户1：系统用户
	private JSONObject result ;

	Logger logger = Logger.getLogger(this.getClass());

	public String adminUserLogin(){
		logger.info("账号:"+loginName+"密码:"+loginPass);	
		result = new JSONObject();
		if(StringUtils.isBlank(loginName)||StringUtils.isBlank(loginPass)){
			return "sys";
		}
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			session.setAttribute("loginName", loginName);
			session.setAttribute("loginPwd", loginPass);
			role = loginService.queryUserRole(loginName, loginPass);
			loginService.doAdminUserLogin(loginName, loginPass, role);	
			logger.info("admin's session:"+session.getAttribute("loginName"));
		}catch (Exception e) {
			logger.error("登录失败",e);
			return "sys";
		}
		return "adminUserJsp";	
	}
	
	public String userLogin(){
		logger.info("userLogin start ·····");
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession(); 
		logger.info("账号"+loginName+"密码："+loginPass);
		if(StringUtils.isBlank(loginName)||StringUtils.isBlank(loginPass)){
			return "userLogin";
		}
		Users user;
		try {
			role = loginService.queryUserRole(loginName, loginPass);
			user = loginService.findByUserNameAndUserPass(loginName, loginPass, role);
			session.setAttribute("loginName",loginName);
			session.setAttribute("loginPass", loginPass);
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userName", user.getLoginName());
			session.setAttribute("userImg", user.getFaceImg());
			logger.info("session:"+session.getAttribute("loginName"));
		} catch (Exception e) {
			logger.info("普通用户登录失败", e);
			return "index";
		}
		return "user";
	}
	
	//注销
	public String loginOut(){
		logger.info("Loginout start·····");
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			logger.info("当前session:"+session.getAttributeNames());
			String loginName = session.getAttribute("loginName").toString();
			String loginPass = session.getAttribute("loginPass").toString();
			role = loginService.queryUserRole(loginName, loginPass);;
			//清除session
			session.invalidate();
		} catch (Exception e) {
			logger.error("清除session异常", e);
		}
		if(role == 1){//系统用户
			return "adminLogin";
		}else{
		return "index";
	}
}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}	
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public String getLoginPass() {
		return loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

}

