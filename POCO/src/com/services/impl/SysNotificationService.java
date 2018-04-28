package com.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dao.SysNotificationDao;
import com.dao.UserDao;
import com.entities.Notification;
import com.entities.Users;
import com.services.ISysNotificationService;
import com.util.UUIDUtil;

public class SysNotificationService implements ISysNotificationService {

	private SysNotificationDao sysNotificationDao;
	
	private UserDao userDao;
	

	@Override
	public Notification showNotificationById(String notificationId) {
		return sysNotificationDao.showNotificationById(notificationId);
	}

	@Override
	public int adds(String notificationTitle, String notificationInfo, String userId, String userName) {
		String uuid = UUIDUtil.generateUUID();
		int count = 0;
		List<Users> userList;
		try {
			userList = userDao.queryUserInfo(Integer.MAX_VALUE, 1);
		} catch (Exception e) {
			e.printStackTrace();
			return count;
		}
		for (Users users : userList) {
			Notification nf = new Notification();
			nf.setNotificationGroupId(uuid);
			nf.setUserId(users.getUserId());
			nf.setCreateTime(new Date());
			nf.setUpdateTime(new Date());
			nf.setUsefulLife(-1);
			nf.setCurStatus("1");
			nf.setNotificationInfo(notificationInfo);
			nf.setNotificationTitle(notificationTitle);
			nf.setCreateUserId(userId);
			nf.setCreateUserName(userName);
			try {
				sysNotificationDao.add(nf);
				count++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return count;
	}
	
	@Override
	public List<Notification> queryNotifiactions(String userId, int role, int currentPage, int recordSize) {
		List<Notification> list = new ArrayList<Notification>();
		try {
			list = sysNotificationDao.queryNotifactions(userId, role, currentPage,recordSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list; 
	}

	@Override
	public int queryTotalNotification(String userId, int role) {
		return sysNotificationDao.queryNotifactionsCount(userId, role);
	}

	@Override
	public List<Notification> notificationListByUserId(String userId, int currentPage, int recordSize) {
		return sysNotificationDao.notificationListByUserId(userId, currentPage, recordSize);
	}
	
	@Override
	public int countReceiver() {
		return sysNotificationDao.doCountReceiver();
	}
	
	@Override
	public int querynotificationCountByUserId(String userId) {
		return sysNotificationDao.doCountNotifications(userId);
	}
	
	@Override
	public void deleteNotificationByNotificationId(String notificationId) {
		sysNotificationDao.doDeleteNotificationByNotificationId(notificationId);
	}



	public SysNotificationDao getSysNotificationDao() {
		return sysNotificationDao;
	}

	public void setSysNotificationDao(SysNotificationDao sysNotificationDao) {
		this.sysNotificationDao = sysNotificationDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
