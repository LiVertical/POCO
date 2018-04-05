package com.services.impl;

import com.dao.RegistDao;
import com.entities.Users;
import com.services.IRegistService;

public class RegistService implements IRegistService{

	private RegistDao registDao;
	private Users user;

	public boolean isOrNotHasUserName(String loginName){
		boolean b = this.registDao.isOrNotHasUserName(loginName);
		return b;
	}
	
	public void saveUserInfo(String loginName,String passWord, int role){
		user = new Users();
		user.setLoginName(loginName);
		user.setLoginPass(passWord);
		user.setRole(role);
		registDao.registSave(user);
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
