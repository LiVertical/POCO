package com.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.actions.LoginAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.util.LoginUserUtil;

public class AuthenticationInterceptor extends MethodFilterInterceptor  {
	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public String doIntercept(ActionInvocation actionInvocation) throws Exception {
		logger.info("AuthenticationInterceptor.doIntercept start······");
		Object action = actionInvocation.getAction();
        if (action instanceof LoginAction) {
          logger.info("登录接口不进行拦截");
            return actionInvocation.invoke();
        }
        // 确认Session中是否存在LOGIN
        Map<String, Object> session = actionInvocation.getInvocationContext().getSession();
        Object login = session.get("APP_USERINFO_SESSION_KEY");
        HttpServletRequest request = ServletActionContext.getRequest();
        if (login != null) {
            // 存在的情况下进行后续操作。
        	 logger.info("登录成功!登录账户："+LoginUserUtil.getUserInfo().getUserName());
            return actionInvocation.invoke();
        } else {
        	if(request.getServletPath().contains("vistor")){
        		logger.info("不需要验证身份");
        		return actionInvocation.invoke();
        	}
            // 否则终止后续操作，返回LOGIN
        	logger.info("未登录被拦截,请先登录!");
        	if(request.getServletPath().contains("user")){
        		return "userLogin";
        	}else{
        		return "adminLogin";
        	}
        }
	}

}
