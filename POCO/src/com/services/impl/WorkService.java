package com.services.impl;

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
	public List<WorksInfos> queryWorks(int currentPage, int recordSize, String workName, String userName,String userId, Integer workType,String biginDate,String endDate) {
		return workDao.doQueryAllWorksInfo(currentPage, recordSize,workName,userName,userId,workType,biginDate,endDate);
	}
	
	@Override
	public int countWorks(String workName, String userName,String userId, Integer workTypeInteger,String biginDate,String endDate) {
		return workDao.doCountWorks(workName,userName,userId,workTypeInteger,biginDate,endDate);
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
	
	@Override
	public List<WorksInfos> queryWorksByActivityId(String activityId) {
		return workDao.queryWorksByActivityId(activityId);
	}
	
	@Override
	public void deleteWorkByWorkId(String workId) {
		workDao.deleteWorkByWorkId(workId);
	}
	
	//批量删除
	@Override
	public void delWorks(String[] proArrayStr) {
		workDao.delWorks(proArrayStr);
	}
	
	@Override
	public List<WorksInfos> doQueryWorksByUser(int currentPage, int recordSize,String userId) {
		return workDao.doQueryWorksInfosByUser(currentPage, recordSize, userId);
	}

	@Override
	public int doCountWorks(String userId) {
		return workDao.doCountWorksSize(userId);
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
