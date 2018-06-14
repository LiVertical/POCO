package com.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.entities.Users;
import com.util.MD5;
import com.util.UUIDUtil;

public class RegistDao extends BaseDao{
	
	Logger logger = Logger.getLogger(this.getClass());
	
	public void registSave(Users user) {
		if(!user.equals(null)){
			getSession().saveOrUpdate(user);
		}
	}
	//用户名是否已经存在
	public boolean isOrNotHasUserName(String loginName){
		boolean b = false;		
		List<Users> list =  getSession().createQuery("FROM Users WHERE loginName='" + loginName + "'").list();
		if(list.size()>0){
			b = true;
		}
		return b;		
	}
	
	public void doAdminUserRegist(String loginName, String userName) {
		try {
			Users user = new Users();
			user.setUserId(UUIDUtil.generateUUID());
			user.setLoginName(loginName);
			user.setRole(1);
			user.setLoginPass(MD5.getMD5ofString("123456"));
			user.setUserName(userName);
			user.setCreateTime(new Date());
			user.setFaceImg("/img/icons/default.jpg");
			getSession().save(user);
		} catch (Exception e) {
			logger.error("添加系统管理员异常", e);
		}
	}
}
