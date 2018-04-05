package com.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.entities.Users;

public class LoginDao extends BaseDao {
	Logger logger = Logger.getLogger(this.getClass());
	private Users user;

	//保存用户信息
	public Users findByUserNameAndUserPass(String loginName,String password, int role){
		user = new Users();
		//按角色保存用户信息
		if(!StringUtils.isBlank(loginName)&&!StringUtils.isBlank(password)){
			Query query = getSession().createQuery("FROM Users WHERE loginName=? AND loginPass=? AND role = ?");
			user = (Users) query.setString(0, loginName).setString(1, password).setInteger(2, role).uniqueResult();	
		}		
		return user;
	}
	//查询用户信息是否存在
	public boolean isOrNotHasUserName(String username){
		boolean b = false;		
		Users user = null;
		Query query = getSession().createQuery("FROM User WHERE username=?");
		user = (Users) query.setString(0, username).uniqueResult();
		if(user != null){
			b = true;
		}
		return b;			
	}
	
	public Users findByUserName(String username){
		Users user = null;
		Query query = getSession().createQuery("FROM User WHERE username=?");
		user = (Users) query.setString(0, username).uniqueResult();		
		return user;
	}
	
	public List<Users> findAdminUserByNameAndPwd(String loginName, String loginPwd, int role){
		String hql = "FROM Users WHERE loginName = ? AND loginPass = ? AND role = ?";
		return getSession().createQuery(hql).setString(0, loginName).setString(1, loginPwd).setInteger(2, role).list();
	}
	
	public Integer doQueryUserRole(String loginName, String loginPass) {
		int role = 0;
		String hql = "FROM Users WHERE loginName=? AND loginPass=?";
		List<Users> user=  getSession().createQuery(hql).setString(0, loginName).setString(1, loginPass).list();
		if(user.size() == 1){
			role = user.get(0).getRole();
		}
		return role;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
}
