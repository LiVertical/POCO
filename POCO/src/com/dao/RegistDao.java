package com.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.entities.Users;
import com.util.UUIDUtil;

public class RegistDao extends BaseDao{
	
	Logger logger = Logger.getLogger(this.getClass());
	
	public void registSave(Users user) {
		if(!user.equals(null)){
			getSession().saveOrUpdate(user);
		}
	}
	//用户名是否已经存在
	public boolean isOrNotHasUserName(String username){
		boolean b = false;		
		Query query = getSession().createQuery("FROM User WHERE username=?");
		query.setParameter(0, username);
		List<Users> list = query.list();
		System.out.println("size="+list.size());
		if(list.size()>0){
			b = true;
		}
		return b;		
	}
	
	public void doAdminUserRegist(String loginName, String loginPwd, String userName) {
		try {
			Users user = new Users();
			user.setUserId(UUIDUtil.generateUUID());
			user.setLoginName(loginName);
			user.setRole(1);
			user.setLoginPass(loginPwd);
			user.setUserName(userName);
			user.setCreateTime(new Date());
			user.setCurStatus("1");
			getSession().save(user);
		} catch (Exception e) {
			logger.error("添加系统管理员异常", e);
		}
	}
}
