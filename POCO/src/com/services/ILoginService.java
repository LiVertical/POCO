package com.services;

import java.util.List;

import com.entities.Users;


public interface ILoginService {

	Users findByUserNameAndUserPass(String loginName, String passWord, int role);

	Users doAdminUserLogin(String loginName, String loginPwd, int role);

	Integer queryUserRole(String loginName, String loginPass);

}
