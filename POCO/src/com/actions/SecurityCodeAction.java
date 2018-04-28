package com.actions;

import java.io.ByteArrayInputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.util.SecurityCode;
import com.util.SecurityImage;


@SuppressWarnings("serial")
public class SecurityCodeAction extends ActionSupport {

	private String securityCode;
	private JSONObject result;
	
	Logger logger = Logger.getLogger(this.getClass());
	//图片流
	private ByteArrayInputStream imageStream;
	
	public String generateCode(){
		logger.info("generateCode start·····");
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			securityCode = SecurityCode.getSecurityCode();
			//把验证码生成图片
			imageStream = SecurityImage.getImageAsInputStream(securityCode);
			//验证码加入session
			//session.setAttribute("securityCode", securityCode);		
			logger.info("验证码："+ securityCode);
		} catch (Exception e) {
			logger.info("验证码生成异常", e);
		}
		return "code";
	}

	public String getSecurityCode() {
		return securityCode;
	}
	
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	
	public JSONObject getResult() {
		return result;
	}
	
	public void setResult(JSONObject result) {
		this.result = result;
	}
	
	public ByteArrayInputStream getImageStream() {
		return imageStream;
	}
	
	public void setImageStram(ByteArrayInputStream imageStream) {
		this.imageStream = imageStream;
	}
	
}
