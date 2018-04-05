package com.services.impl;

import java.util.List;

import com.dao.SysNotificationDao;
import com.entities.Notifiaction;
import com.services.ISysNotificationService;

public class SysNotificationService implements ISysNotificationService {

	private SysNotificationDao sysNotificationDao;
	
	@Override
	public List<Notifiaction> notificationListByUserId(int userId) {
		List<Notifiaction> notificationList = sysNotificationDao.notificationListByUserId(userId);
		return notificationList;
	}

	@Override
	public Notifiaction showNotificationById(String notifiactionId) {
		Notifiaction notifiaction = sysNotificationDao.showNotificationById(notifiactionId);
		return notifiaction;
	}

	@Override
	public void add(Notifiaction notifiaction) {
		sysNotificationDao.add(notifiaction);
	}

}
