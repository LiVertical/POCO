package com.services.impl;

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
	public int adds(Notifiaction notifiaction) {
		String flag = "Y";
		int count = 0;
		List<Users> userList;
		try {
			userList = userDao.getAllByPage(Integer.MAX_VALUE, 1);
		} catch (Exception e1) {
			e1.printStackTrace();
			return count;
		}
		
		for (Users users : userList) {
			String uuid = UUIDUtil.generateUUID();
			if(flag.equals("Y")){
				notifiaction.setNotifiactionGroupId(uuid);
				flag = uuid;
			}else{
				notifiaction.setNotifiactionGroupId(uuid);
			}
			notifiaction.setNotifiactionId(uuid);
			notifiaction.setUserId(users.getUserId());
			try {
				sysNotificationDao.add(notifiaction);
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

	
}
