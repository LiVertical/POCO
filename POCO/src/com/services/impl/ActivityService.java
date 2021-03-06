package com.services.impl;

import java.io.File;
import java.util.List;

import com.dao.ActivityDao;
import com.dao.UserDao;
import com.entities.Activities;
import com.services.IActivityService;
import com.vo.ActivityInfoVo;

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
	public List<ActivityInfoVo> queryAllActivitiesByCondition(int currentPage,int recordSize,Integer auditStatus) {
		return activityDao.doQueryAllActivities(currentPage,recordSize,auditStatus);
	}

	@Override
	public int queryAllActivitiesCount() {
		return activityDao.doQueryAllActivitiesCount();
	}
	
	//审批活动
	@Override
	public void doAuditActivity(String activityId, int curStatus) {
		activityDao.auditActivity(activityId, curStatus);
	}
	
	@Override
	public List<Activities> queryAllActivities() {
		return activityDao.getAllActivities();
	}
	
	@Override
	public Activities doQueryActivityInfo(String activityId) {
		return activityDao.queryActivityById(activityId);
//		return activityDao.queryActivityInfos(activityId);
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
