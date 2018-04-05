package com.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import com.entities.Users;

public class RegistDao extends BaseDao{
	
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
			user.setLoginName(loginName);
			user.setRole(1);
			user.setLoginPass(loginPwd);
			user.setUserName(userName);
			Date date = new Date();
			user.setCreateTime(date);
			user.setCurStatus("1");
			getSession().save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
