package com.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.entities.Users;
import com.util.MD5;

public class LoginDao extends BaseDao {
	Logger logger = Logger.getLogger(this.getClass());
	private Users user;

	//保存用户信息
	public Users findByUserNameAndUserPass(String loginName,String password, int role){
		user = new Users();
		//按角色查找用户信息
		try {
			if(!StringUtils.isBlank(loginName)&&!StringUtils.isBlank(password)){
				String sql = "FROM Users WHERE loginName='" + loginName+"' AND loginPass='"+MD5.getMD5ofString(password)+"' AND role = "+role;
				user = (Users) getSession().createQuery(sql).list().get(0);
			}
		} catch (HibernateException e) {
			logger.error("查询用户信息异常", e);
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
	
	public Users findAdminUserByNameAndPwd(String loginName, String loginPwd, int role){
		String hql = "FROM Users WHERE loginName = ? AND loginPass = ? AND role = ?";
		List<Users> list = (List<Users>)getSession().createQuery(hql).setString(0, loginName).setString(1, loginPwd).setInteger(2, role).list();
		if(list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
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
