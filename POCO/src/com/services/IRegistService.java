package com.services;

public interface IRegistService {
	
	//保存用户信息
	void saveUserInfo(String loginName,String password, int role);

	void doAdminUserRegist(String loginName, String loginPwd, String userName);

}
