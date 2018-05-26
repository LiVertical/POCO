package com.services.impl;

import com.dao.VoteDao;
import com.services.IVoteService;

public class VoteService implements IVoteService {
	
	private VoteDao voteDao;

	public VoteDao getVoteDao() {
		return voteDao;
	}

	public void setVoteDao(VoteDao voteDao) {
		this.voteDao = voteDao;
	}

	@Override
	public boolean doVote(String userId, String workId) {
		//查询该用户是否已经给该作品投票
		boolean voted = false;
		voted = voteDao.findWorkByUserId(workId,userId);
		if(voted){
			voteDao.saveVote(workId, userId);
		}
		return voted;
	}

	@Override
	public int getVoteNum(String workId) {
		int size = voteDao.getVoteNum(workId);
		return size;
	}

}
