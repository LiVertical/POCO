package com.dao;

import java.util.List;

import com.entities.Notifiaction;

public class SysNotificationDao extends BaseDao{

	/**
	 * 根据userId查询系统通知集合，需要根据创建时间倒叙排序
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Notifiaction> notificationListByUserId(int userId){
		String hql = "FROM Notifiaction as n WHERE n.userId=? order by createTime desc";
		List<Notifiaction> list = (List<Notifiaction>) getSession().createQuery(hql).setInteger(0, userId).list();
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

}
