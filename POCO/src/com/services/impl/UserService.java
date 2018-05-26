package com.services.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.dao.UserDao;
import com.entities.Users;
import com.services.IUserService;

public class UserService implements IUserService{
	
	private UserDao userDao;
	Logger logger = Logger.getLogger(this.getClass());
	
	public List<Users> queryUserByCondition(int recordSize, int currentPage,String userName){
		List<Users> userInfos =  userDao.queryUserInfo(recordSize, currentPage, userName);
	    return userInfos;
	}
	
	//根据id删除用户信息
	public void doDeleteUserInfo(String userId){
		userDao.delete(userId);
	}

	//上传用户头像
	public void saveOrUpdateUserImg(String userid,String faceimg){		
		userDao.saveOrUpdateUserImg(userid,faceimg);
	}

	//修改用户信息
	public void updateUser(Users user){
		userDao.updateUser(user);
	}
	
	//根据id查询用户
	public Users getUserById(String userId){
		return userDao.getUserById(userId);
	}
	
	
	@Override
	public void doSaveOrUpdateUserInfo(String userId, int age, String email, int sex) {
		userDao.saveOrUpdateUserInfo(userId, age, email,sex);
	}
	
	//获取所有用户
	public List<Users> getAllByPage(int pageSize,int page){
		List<Users> users = userDao.getAllByPage(pageSize, page);
		return users;
	}
	
	//更新密码
	@Override
	public void doUpdatePass(String userId, String newPass) {
		userDao.doUpdate(userId, newPass);
	}
	
	@Override
	public int countUser() {
		return userDao.getCountUser();
	}
	
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
