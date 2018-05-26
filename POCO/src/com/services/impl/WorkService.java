package com.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.dao.UserDao;
import com.dao.WorkDao;
import com.entities.Work;
import com.services.IWorkService;
import com.vo.WorksInfos;
import com.vo.workDescInfoVo;

public class WorkService implements IWorkService {
	
	private UserDao userDao;
	private WorkDao workDao;
	
	@Override
	public void doAddWork(String userId, String workName, String workComment,String productGroupId, String activityId, String contestId) {
		workDao.addWork(userId, workName, workComment, productGroupId, activityId, contestId);
	}
	
	@Override
	public List<Work> queryProductByWorkType(Integer workType) {
		return workDao.getWorksByWorkType(workType);
	}
	
	@Override
	public List<WorksInfos> queryWorks(int currentPage, int recordSize, String workName, String userName, Integer workType) {
		return workDao.doQueryAllWorksInfo(currentPage, recordSize,workName,userName,workType);
	}
	
	@Override
	public int countWorks() {
		return workDao.doCountWorks();
	}

	@Override
	public List<workDescInfoVo> queryWorksInfo(int currentPage, int recordSize) {
		return workDao.queryAllWorksInfo(currentPage, recordSize);
	}
	
	@Override
	public List<WorksInfos> doQueryWorksInfoByContestId(String contestId) {
		return workDao.queryWorkInfosByContestId(contestId);
	}
	
	@Override
	public WorksInfos queryWorksInfoByWorkId(String workId) {
		return workDao.queryWorkInfosByWorkId(workId);
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
