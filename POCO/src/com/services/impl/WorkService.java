package com.services.impl;

import com.dao.UserDao;
import com.dao.WorkDao;
import com.services.IWorkService;

public class WorkService implements IWorkService {
	
	private UserDao userDao;
	private WorkDao workDao;
	
	@Override
	public void doAddWork(String userId, String workName, String workComment,String productGroupId) {
		workDao.addWork(userId, workName, workComment, productGroupId);
	}
	
	
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public WorkDao getWorkDao() {
		return workDao;
	}
	public void setWorkDao(WorkDao workDao) {
		this.workDao = workDao;
	}
}
