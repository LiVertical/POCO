package com.services;

import java.util.Date;
import java.util.List;

import com.entities.Contests;

public interface IContestService {

	void doPostContest(String contestName, String contestDesc, Date startTime, Date endTime);

	List<Contests> queryAllContest();

	Contests queryContestInfoByContestId(String contestId);

}
