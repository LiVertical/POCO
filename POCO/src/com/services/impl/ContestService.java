package com.services.impl;

import java.util.Date;
import java.util.List;

import com.dao.ContestDao;
import com.entities.Contests;
import com.services.IContestService;

public class ContestService implements IContestService{

	private ContestDao contestDao;
	
	@Override
	public void doPostContest(String contestName, String contestDesc,Date startTime, Date endTime) {
			contestDao.saveContest(contestName, contestDesc,startTime,endTime);
	}
	
	@Override
	public List<Contests> queryAllContest() {
		return contestDao.queryAllContest();
	}
	
	public ContestDao getContestDao() {
		return contestDao;
	}

	public void setContestDao(ContestDao contestDao) {
		this.contestDao = contestDao;
	}

	@Override
	public Contests queryContestInfoByContestId(String contestId) {
		return contestDao.doQueryContestInfo(contestId);
	}

}
