package com.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import com.dao.jdbc.BaseJdbcDao;
import com.entities.Notifiaction;

public class SysNotificationDao extends BaseJdbcDao{

	/**
	 * 根据userId查询系统通知集合，需要根据创建时间倒叙排序
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Notifiaction> notificationListByUserId(String userId){
		String hql = "FROM Notifiaction as n WHERE n.userId=?";
		List<Notifiaction> list = (List<Notifiaction>) getSession().createQuery(hql).setString(0, userId).list();
		return list;
	}

	/**
	 * 根据ID查询系统通知
	 * @param notifiactionId
	 * @return
	 */
	public Notifiaction showNotificationById(String notifiactionId) {
		String hql = "FROM Notifiaction as n WHERE n.notifiactionId=?";
		List<Notifiaction> list = (List<Notifiaction>) getSession().createQuery(hql).setString(0, notifiactionId).list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public void add(Notifiaction notifiaction) {
		this.getSession().save(notifiaction);
	}

	public void delete(Notifiaction notifiaction2) {
		String hql = "update Notifiaction as n set n.curStatus=? ,n.updateTime=? ,n.updateUser=? where n.notifiactionId=? or n.notifiactionGroupId=?";
		getSession().createQuery(hql).setString(0, "0").setDate(1, new Date()).setString(2, notifiaction2.getUserId()).setString(3, notifiaction2.getNotifiactionId()).setString(4, notifiaction2.getNotifiactionGroupId());
	}

	@SuppressWarnings("unchecked")
	public List<Notifiaction> queryNotifactions(int currentPage, int recordSize) {
		List<Notifiaction> list = new ArrayList<Notifiaction>();
		try {
			String hql = "FROM Notifiaction as n GROUP BY n.notifiactionGroupId ORDER BY n.createTime DESC";
			list = (List<Notifiaction>) getSession().createQuery(hql).setFirstResult(currentPage).setMaxResults(recordSize).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return list;
	}

}
