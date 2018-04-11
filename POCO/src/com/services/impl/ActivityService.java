package com.services.impl;

import java.io.File;
import java.util.List;

import com.dao.ActivityDao;
import com.dao.UserDao;
import com.entities.Activities;
import com.services.IActivityService;

public class ActivityService implements IActivityService {
	private UserDao userDao;
	private ActivityDao activityDao;
	
	@Override
	public void doSaveActivity(String activityName, List<File> products,
			List<String> productIds, List<String> productNames, String userId,
			String activityDesc, String activityInfo, String createTime,
			String endTime) {
		activityDao.saveActivity(activityName, products, productIds, productNames, userId, activityDesc, activityInfo, createTime, endTime);
	}
	
	@Override
	public void doApplyActivity(String userId, String activityName, String activityDesc, String createTime, String endTime) {
		activityDao.saveActivity(activityName, userId, activityDesc, createTime, endTime);
	}
	
	@Override
	public List<Activities> queryAllActivitiesByCondition(int currentPage,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int queryAllActivitiesCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
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
