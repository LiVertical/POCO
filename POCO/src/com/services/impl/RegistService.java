package com.services.impl;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.dao.RegistDao;
import com.entities.Users;
import com.services.IRegistService;
import com.util.MD5;

public class RegistService implements IRegistService{

	Logger logger = Logger.getLogger(this.getClass());
	private RegistDao registDao;
	private Users user;

	public boolean isOrNotHasUserName(String loginName){
		boolean b = this.registDao.isOrNotHasUserName(loginName);
		return b;
	}
	
	public void saveUserInfo(String loginName,String passWord, int role){
		user = new Users();
		user.setLoginName(loginName);
		try {
			user.setLoginPass(MD5.encodeCN(passWord).toString());	
			user.setRole(role);
			registDao.registSave(user);
		} catch (UnsupportedEncodingException e) {
			logger.error("保存用户信息异常", e);
		}
	
	}
	
	@Override
	public void doAdminUserRegist(String loginName, String loginPwd, String userName) {
		registDao.doAdminUserRegist(loginName, loginPwd, userName);
	}
	
	public RegistDao getRegistDao() {
		return registDao;
	}

	public void setRegistDao(RegistDao registDao) {
		this.registDao = registDao;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}


}
