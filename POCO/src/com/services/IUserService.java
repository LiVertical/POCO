package com.services;

import java.util.List;
import java.util.Map;

import com.entities.Users;

public interface IUserService {
	
	//分页查询用户信息
	List<Users> queryUserByCondition(int recordSize, int currentPage, String userName);

	//上传用户头像
	void saveOrUpdateUserImg(String userId, String url);

	Users getUserById(String userId);

	void doDeleteUserInfo(String userId);

	//更新用户信息
	void doSaveOrUpdateUserInfo(String userId, String userName, int age, String email, int sex);

	//更新用户密码
	void doUpdatePass(String userId, String newPass);

	int countUser();

}
