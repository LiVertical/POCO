package com.services.impl;

import java.util.List;

import com.dao.UserDao;
import com.dao.WorkDao;
import com.entities.Work;
import com.services.IWorkService;

public class WorkService implements IWorkService {
	
	private UserDao userDao;
	private WorkDao workDao;
	
	@Override
	public void doAddWork(String userId, String workName, String workComment,String productGroupId) {
		workDao.addWork(userId, workName, workComment, productGroupId);
	}
	
	@Override
	public List<Work> queryProductByWorkType(int workType) {
		return workDao.getWorksByWorkType(workType);
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
