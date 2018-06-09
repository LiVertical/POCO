package com.dao;

import java.util.Date;
import java.util.List;

import com.constants.ContestConstants;
import com.entities.Contests;
import com.util.UUIDUtil;

public class ContestDao extends BaseDao{

	public void saveContest(String contestName, String contestDesc, Date startTime, Date endTime) {
		Contests contest = new Contests();
		contest.setContestId(UUIDUtil.generateUUID());
		contest.setContestName(contestName);
		contest.setContestDesc(contestDesc);
		contest.setPostStatus(ContestConstants.CONTEST_STATUS_NOT_CHECK);
		contest.setCreateTime(new Date());
		contest.setStartTime(startTime);
		contest.setEndTime(endTime);
		this.getSession().save(contest);
	}

	public List<Contests> queryAllContest() {
		String sql = "FROM Contests ORDER BY startTime DESC";
		return getSession().createQuery(sql).list();
	}

	public Contests doQueryContestInfo(String contestId) {
		String sql = "FROM Contests WHERE contestId='"+contestId+"'";
		return (Contests) getSession().createQuery(sql).list().get(0);
	}

}
