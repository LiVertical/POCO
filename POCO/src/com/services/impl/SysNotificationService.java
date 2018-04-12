package com.services.impl;

import java.util.Date;
import java.util.List;

import com.dao.SysNotificationDao;
import com.dao.UserDao;
import com.entities.Notifiaction;
import com.entities.Users;
import com.services.ISysNotificationService;
import com.util.UUIDUtil;

public class SysNotificationService implements ISysNotificationService {

	private SysNotificationDao sysNotificationDao;
	
	private UserDao userDao;
	
	@Override
	public List<Notifiaction> notificationListByUserId(String userId) {
		List<Notifiaction> notificationList = sysNotificationDao.notificationListByUserId(userId);
		return notificationList;
	}

	@Override
	public Notifiaction showNotificationById(String notifiactionId) {
		Notifiaction notifiaction = sysNotificationDao.showNotificationById(notifiactionId);
		return notifiaction;
	}

	@Override
	public int adds(Notifiaction notifiaction) {
		String uuid = UUIDUtil.generateUUID();
		int count = 0;
		List<Users> userList;
		try {
			userList = userDao.queryUserInfo(Integer.MAX_VALUE, 1);
		} catch (Exception e1) {
			e1.printStackTrace();
			return count;
		}
		
		for (Users users : userList) {
			Notifiaction nf = new Notifiaction();
			nf.setNotifiactionGroupId(uuid);
			nf.setUserId(users.getUserId());
			nf.setCreateTime(new Date());
			nf.setUsefulLife(-1);
			nf.setCurStatus("1");
			nf.setNotifiactionInfo(notifiaction.getNotifiactionInfo());
			nf.setNotifiactionTitle(notifiaction.getNotifiactionTitle());
			nf.setCreateUser(notifiaction.getCreateUser());
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
	public void delete(Notifiaction notifiaction2) {
		
		sysNotificationDao.delete(notifiaction2);
		
		
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

	@Override
	public List<Notifiaction> queryNotifiactions(int currentPage, int recordSize) {
		List<Notifiaction> list = sysNotificationDao.queryNotifactions(currentPage,recordSize);
		
//		for (Notifiaction notifiaction : list) {
//			Users user = userDao.getUserById(notifiaction.getCreateUser());
//			notifiaction.setCreateUser(user.getUserName());
//		}
		return list; 
	}

	
}
