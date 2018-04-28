package com.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.entities.Users;
import com.util.MD5;

public class UserDao extends BaseDao{
	
	Logger logger = Logger.getLogger(this.getClass());
	
	//删除用户信息
	public void delete(String userId) {		
		String hql = "DELETE FROM Users u WHERE u.userId=?";
		getSession().createQuery(hql).setString(0, userId).executeUpdate();
	}
	
	//上传用户头像
	public void saveOrUpdateUserImg(String userid,String faceimg){
		String hql = "update Users u set u.faceImg = ? where u.userId = ?";
		getSession().createQuery(hql).setString(0, faceimg).setString(1, userid).executeUpdate();
	}
	
	//更新用户信息
	public void updateUser(Users user){
		getSession().saveOrUpdate(user);
	}
		
	//查询用户
	public Users getUserById(String userId){			
		return (Users) getSession().get(Users.class, userId);
	}
	
	//查询用户信息
	public List<Users> getAllByPage(int pageSize,int page){
		String hql = "FROM Users u LEFT OUTER JOIN FETCH u.role";
		Query query = getSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Users> users = query.setFirstResult((page-1)*pageSize).setMaxResults(pageSize).list();
		System.out.println(users);
		return users;		
	}

	public List<Users> queryUserInfo(int recordSize, int currentPage){
		String hql = "FROM Users";
		List<Users> list = getSession().createQuery(hql).setFirstResult((currentPage-1)*recordSize).setMaxResults(recordSize).list();
		return list;
	}

	public void saveOrUpdateUserInfo(String userId, int age, String email, int sex) {
		String hql = "update Users u set u.age = ?,u.email=?,u.sex=? where u.userId = ?";
		getSession().createQuery(hql).setInteger(0, age).setString(1, email).setInteger(2, sex).setString(3, userId).executeUpdate();
	}

	public void doUpdate(String userId, String newPass) {
		try {
			String hql = "update Users u set u.loginPass = '" + MD5.getMD5ofString(newPass)+"' WHERE u.userId='" + userId + "'";
			getSession().createQuery(hql).executeUpdate();
		} catch (HibernateException e) {
			logger.error("更新用户密码异常", e);
		}
	}

	public int getCountUser() {
		String sql = "FROM Users";
		int size = 0;
		size = getSession().createQuery(sql).list().size();
		return size;
	}


}