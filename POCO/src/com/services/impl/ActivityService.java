package com.services.impl;

import com.dao.ActivityDao;
import com.dao.UserDao;
import com.services.IActivityService;

public class ActivityService implements IActivityService {
	private UserDao userDao;
	private ActivityDao activityDao;
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public ActivityDao getActivityDao() {
		return activityDao;
	}
	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

}
