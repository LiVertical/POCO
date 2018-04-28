package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import com.dao.jdbc.BaseJdbcDao;
import com.entities.Notification;

public class SysNotificationDao extends BaseJdbcDao{

	/**
	 * 根据userId查询系统通知集合，需要根据创建时间倒叙排序
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Notification> notificationListByUserId(String userId , int currentPage, int recordSize){
		List<Notification> list = null;
		try {
			String hql = "FROM Notification as n WHERE n.userId= '"+userId + "'";
			list = (List<Notification>) getSession().createQuery(hql).setFirstResult((currentPage-1)*recordSize).setMaxResults(recordSize).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据ID查询系统通知
	 * @param notifiactionId
	 * @return
	 */
	public Notification showNotificationById(String notificationId) {
		Notification notification = null;
		try {
			String hql = "FROM Notification as n WHERE n.notificationId= '" + notificationId + "'";
			notification = (Notification) getSession().createQuery(hql).list().get(0);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return notification;
	}
                     
	public void add(Notification notifiaction) {
		this.getSession().save(notifiaction);
	}

	@SuppressWarnings("unchecked")
	public List<Notification> queryNotifactions(String userId, int role, int currentPage, int recordSize) {
		List<Notification> list = new ArrayList<Notification>();
		try {
			String hql = "FROM Notification as n GROUP BY n.notificationGroupId ORDER BY n.createTime DESC";
			if(role != 2){
				hql = "FROM Notification as n WHERE n.userId='"+userId+"' GROUP BY n.notificationGroupId ORDER BY n.createTime DESC ";
			}
			list = (List<Notification>) getSession().createQuery(hql).setFirstResult((currentPage-1)*recordSize).setMaxResults(recordSize).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int queryNotifactionsCount(String userId, int role) {
		String sql = "FROM Notification GROUP BY notificationGroupId";
		if(role != 2){
			sql = "FROM Notification as n WHERE n.userId='"+userId+"' GROUP BY n.notificationGroupId ORDER BY n.createTime DESC ";
		}
		int size = 0;
		size = getSession().createQuery(sql).list().size();
		return size;
	}
	
	public int doCountReceiver() {
		String sql = "FROM Notification GROUP BY userId";
		int size = 0;
		size = getSession().createQuery(sql).list().size();
		return size;
	}

	public int doCountNotifications(String userId) {
		String hql = "FROM Notification WHERE userId= '"+userId+"'";
		return getSession().createQuery(hql).list().size();
	}

	public void doDeleteNotificationByNotificationId(String notifiactionId) {
		String hql = "DELETE FROM Notification  WHERE notificationId='"+notifiactionId+"'";
		getSession().createQuery(hql).executeUpdate();
	}


}
