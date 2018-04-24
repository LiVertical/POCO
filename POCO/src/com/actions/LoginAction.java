package com.actions;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.Cookie;
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
			logger.info("参数错误");
			return "sys";
		}
		if(role != 1 && role != 2){
			logger.info("权限错误");
			return "sys";
		}
		try {
			UserInfo userInfo = new UserInfo();
			role = loginService.queryUserRole(loginName, loginPass);
			Users adminLogin = loginService.doAdminUserLogin(loginName, loginPass, role);
			if(adminLogin != null){
				userInfo.setLoginName(loginName);
				userInfo.setUserName(adminLogin.getUserName());
				userInfo.setPassword(loginPass);
				userInfo.setRole(role);
				userInfo.setUserId(adminLogin.getUserId());
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
		logger.info("LoginAction.userLogin start ·····账号"+loginName+"密码："+loginPass);
		if(StringUtils.isBlank(loginName)||StringUtils.isBlank(loginPass)){
			logger.info("参数错误");
			return "login";
		}
		try {
			UserInfo cuserInfo = new UserInfo();
			role = loginService.queryUserRole(loginName, loginPass);
			if(role == 1 || role == 2){
				logger.info("权限错误");
				return "user";
			}
			Users user = loginService.findByUserNameAndUserPass(loginName, loginPass, role);
			if(user != null){
				cuserInfo.setLoginName(loginName);
				cuserInfo.setPassword(loginPass);
				cuserInfo.setRole(role);
				cuserInfo.setUserId(user.getUserId());
				cuserInfo.setUserName(user.getUserName());
				cuserInfo.setUserImg(user.getFaceImg());
				LoginUserUtil.saveUserInfo(getContext(), cuserInfo);
			}else{
				throw new Exception("帐号密码错误");
			}
		} catch (Exception e) {
			logger.error("普通用户登录失败", e);
			return "index";
		}
		return "user";
	}
	
	//注销
	public String loginOut(){
		logger.info("LoginAction.loginOut start·····");
		try {
            Map session =  ActionContext.getContext().getSession();
            if(session.get("USERINFO_SESSION_KEY") != null){
            	 role = LoginUserUtil.getUserInfo().getRole();
            } 
            session.remove("USERINFO_SESSION_KEY");
		} catch (Exception e) {
			logger.error("清除session异常", e);
		}
		if(role == 1 || role == 2){//系统用户
			return "adminLogin";
		}else{
			return "index";
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

