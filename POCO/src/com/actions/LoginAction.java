package com.actions;

import java.io.InputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.entities.Users;
import com.opensymphony.xwork2.ActionContext;
import com.services.ILoginService;
import com.util.LoginUserUtil;
import com.util.UserInfo;

/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class LoginAction extends BaseAction{
	
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
			UserInfo userInfo = new UserInfo();
			role = loginService.queryUserRole(loginName, loginPass);
			Users userLogin = loginService.doAdminUserLogin(loginName, loginPass, role);
			if(userLogin != null){
				session.setAttribute("loginName", userLogin.getUserName());
				session.setAttribute("userId", userLogin.getUserId());
				logger.info("admin's session:"+session.getAttribute("loginName"));
				userInfo.setLoginName(loginName);
				userInfo.setUserName(userLogin.getUserName());
				userInfo.setPassword(loginPass);
				userInfo.setRole(role);
				userInfo.setUserId(userLogin.getUserId());
				LoginUserUtil.saveUserInfo(getContext(), userInfo);
			}else{
				throw new Exception("帐号密码错误");
			}
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
		logger.info("LoginAction.loginOut start·····");
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
		return "logOut";
		}
	}
	
	//检测是否是登录状态
	public String checkIsLogin(){
		logger.info("LoginAction.checkIsLogin start·····");
		result = new JSONObject();
		try {
			if(LoginUserUtil.getUserInfo() == null){
				logger.info("用户没有登录,转到登录界面");
			    result.put("returnCode", "10");
			    result.put("returnMsg", "用户没有登录");
			 }else{
				logger.info("当前登录账户：" + LoginUserUtil.getUserInfo().getLoginName());
				 result.put("returnCode", "00");
				 result.put("returnMsg", "用户处于登录状态");
			}
		} catch (Exception e) {
			logger.error("检测用户是否登录异常");
		}
		return outputResult(result.toString());
	}
	
	private void setCookie(String cookieName,String cookieValue,Cookie[] cooks){
    	int i=0;
        for(Cookie cook:cooks){
            if(cook.getName().equals(cookieName)){
                i=1;
            }
 
        }
        if(i==0){
            Cookie cook = new Cookie(cookieName, cookieValue);
            cook.setMaxAge(7*24*60*60);
            cook.setPath("/");
            ServletActionContext.getResponse().addCookie(cook);
        }else{
            for(Cookie cook:cooks){
                if(cook.getName().equals(cookieName)){
                    if(!cook.getValue().equals(cookieValue)){
                        cook.setValue(cookieValue);
                        cook.setMaxAge(10*24*60*60);
                        ServletActionContext.getResponse().addCookie(cook);
                    }
                }
            }   
        }
    }
    
    public String statisticIndex(){
    	logger.info("start loginAction.statisticIndex...");
        return SUCCESS;
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

