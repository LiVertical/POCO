package com.services.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.dao.LoginDao;
import com.entities.Users;
import com.services.ILoginService;
import com.util.MD5;

public class LoginService implements ILoginService{
	private LoginDao loginDao;
	
	public Users findByUserNameAndUserPass(String loginName,String passWord, int role){
		if(StringUtils.isBlank(loginName)||StringUtils.isBlank(passWord)||StringUtils.isBlank(String.valueOf(role))){
			return null;
		}
		return loginDao.findByUserNameAndUserPass(loginName, passWord, role);	
	}
	
	public Users findUser(String username){
		Users user = loginDao.findByUserName(username);
		return user;
		
	}
	public boolean isOrNotHasUserName(String username){
		boolean b = loginDao.isOrNotHasUserName(username);
		return b;
	}
	
	/**
	 * 查詢用戶身份
	 * @param loginName
	 * @param loginPass
	 * @param role
	 * @return
	 */
	@Override
	public Users doAdminUserLogin(String loginName, String loginPass, int role) {		
		return loginDao.findAdminUserByNameAndPwd(loginName, MD5.getMD5ofString(loginPass), role);
	}

	@Override
	public Integer queryUserRole(String loginName, String loginPass) {
		int role = 0;
		role = loginDao.doQueryUserRole(loginName, MD5.getMD5ofString(loginPass)); 
		return role;
	}

	public LoginDao getLoginDao() {
		return loginDao;
	}
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}


}













