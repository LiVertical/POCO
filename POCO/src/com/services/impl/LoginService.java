package com.services.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.dao.LoginDao;
import com.entities.Users;
import com.services.ILoginService;

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
	
	@Override
	public List<Users> doAdminUserLogin(String loginName, String loginPass, int role) {
		return loginDao.findAdminUserByNameAndPwd(loginName, loginPass, role);
	}
	
	//查询用户身份
	@Override
	public Integer queryUserRole(String loginName, String loginPass) {
		int role = 0;
		role = loginDao.doQueryUserRole(loginName, loginPass); 
		return role;
	}

	public LoginDao getLoginDao() {
		return loginDao;
	}
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}


}













