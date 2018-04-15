package com.util;

import org.hibernate.service.spi.ServiceException;
import com.entities.Users;
import com.opensymphony.xwork2.ActionContext;

public class LoginUserUtil {
	
	public final static String COMMONUSER_SESSION_KEY = "APP_COMMONUSERINFO_SESSION_KEY";
    public final static String USERINFO_SESSION_KEY = "APP_USERINFO_SESSION_KEY";
	
	public static void saveUserInfo(ActionContext context, UserInfo user){
        if (context == null){
            throw new ServiceException("AcitonContext为空！");
        }
        context.getSession().put(USERINFO_SESSION_KEY, user);
    }
	
	public static UserInfo getUserInfo(){
    	ActionContext context = ActionContext.getContext();
        if (context == null)
        {
            throw new ServiceException("AcitonContext为空！");
        }
        return (UserInfo) context.getSession().get(USERINFO_SESSION_KEY);
    }
	
	public static void saveCommonUserInfo(ActionContext context, CommonUserInfo cuserInfo){
        if (context == null){
            throw new ServiceException("AcitonContext为空！");
        }
        context.getSession().put(COMMONUSER_SESSION_KEY, cuserInfo);
    }
	
	public static UserInfo getCommonUserInfo(){
    	ActionContext context = ActionContext.getContext();
        if (context == null)
        {
            throw new ServiceException("AcitonContext为空！");
        }
        return (UserInfo) context.getSession().get(COMMONUSER_SESSION_KEY);
    }
	
	public static Users getLoginAdminUser(ActionContext context)
    {
        if (context == null)
        {
            throw new ServiceException("AcitonContext为空");
        }
        return (Users) context.getSession().get(USERINFO_SESSION_KEY);
    }
	
	public static Users getLoginCommonUser(ActionContext context)
    {
        if (context == null)
        {
            throw new ServiceException("AcitonContext为空");
        }
        return (Users) context.getSession().get(COMMONUSER_SESSION_KEY);
    }

}
